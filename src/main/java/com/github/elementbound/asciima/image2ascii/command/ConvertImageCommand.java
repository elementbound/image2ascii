package com.github.elementbound.asciima.image2ascii.command;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.command.context.ContextProvider;
import com.github.elementbound.asciima.image2ascii.command.context.model.ContextRequest;
import com.github.elementbound.asciima.image2ascii.command.context.model.ContextResponse;
import com.github.elementbound.asciima.image2ascii.command.model.CharacterSet;
import com.github.elementbound.asciima.image2ascii.command.model.CharacterWeightFunctionType;
import com.github.elementbound.asciima.image2ascii.command.model.ColorMapperType;
import com.github.elementbound.asciima.image2ascii.command.model.Palette;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements ConsoleCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertImageCommand.class);
    private static final String POSSIBLE_VALUES = "Possible values: ${COMPLETION-CANDIDATES}";

    @Parameters(index = "0", description = "Input file", paramLabel = "input")
    private Path inputPath;

    @Option(names = {"-cs", "--characterSet"}, description = POSSIBLE_VALUES)
    private CharacterSet characterSet = CharacterSet.GRADIENT;

    @Option(names = {"-cw", "--characterWeight"}, description = POSSIBLE_VALUES)
    private CharacterWeightFunctionType characterWeightFunctionType = CharacterWeightFunctionType.GRADIENT;

    @Option(names = {"-cm", "--colorMapper"}, description = POSSIBLE_VALUES)
    private ColorMapperType colorMapperType = ColorMapperType.NEAREST;

    @Option(names = {"-p", "--palette"}, description = POSSIBLE_VALUES)
    private Palette palette = Palette.ASCII_16;

    private final ContextProvider contextProvider;
    private final RGBColorFactory rgbColorFactory;

    private PrimaryColorFinder primaryColorFinder;
    private ImageColorMapper imageColorMapper;
    private CharacterRecognizer characterRecognizer;
    private CharacterEncoder characterEncoder;

    public ConvertImageCommand(ContextProvider contextProvider, RGBColorFactory rgbColorFactory) {
        this.contextProvider = contextProvider;
        this.rgbColorFactory = rgbColorFactory;
    }

    private void gatherContext() {
        ContextResponse context = contextProvider.getContext(ContextRequest.builder()
                .characterSet(characterSet)
                .characterWeightFunctionType(characterWeightFunctionType)
                .colorMapperType(colorMapperType)
                .palette(palette)
                .build()
        );

        this.primaryColorFinder = context.getPalettePrimaryColorFinder();
        this.imageColorMapper = context.getImageColorMapper();
        this.characterRecognizer = context.getCharacterRecognizer();
        this.characterEncoder = context.getCharacterEncoder();
    }

    @Override
    public Integer call() throws IOException {
        gatherContext();

        BufferedImage image = ImageIO.read(inputPath.toFile());

        int tileWidth = 6;
        int tileHeight = 10;

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;
        int tileCount = tileCountX * tileCountY;

        AtomicInteger tileCounter = new AtomicInteger(0);
        BiFunction<Integer, Integer, String> tileProcessor = curryProcessTile(image, tileWidth, tileHeight);
        BiFunction<Integer, Integer, String> wrappedTileProcessor = (x, y) -> {
            String result = tileProcessor.apply(x, y);
            int progress = tileCounter.incrementAndGet();

            if (progress % (tileCount / 100) == 0) {
                LOGGER.info("Progress: {}/{} = {}%", progress, tileCount, progress * 100 / tileCount);
            }

            return result;
        };

        String result = IntStream.range(0, tileCountY)
                .parallel()
                .mapToObj(row -> ImmutablePair.of(row, IntStream.range(0, tileCountX)))
                .map(pair -> pair.getRight().mapToObj(x -> wrappedTileProcessor.apply(x, pair.getLeft())))
                .map(row -> row.collect(Collectors.joining()))
                .collect(Collectors.joining("\u001B[0m\n"));

        System.out.println(result);

        return 0;
    }

    private String processTile(BufferedImage image, int tileWidth, int tileHeight, int x, int y) {
        BufferedImage tile = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        RGBColor[] tileColors = primaryColorFinder.findPrimaryColors(tile, 2).toArray(new RGBColor[2]);
        BufferedImage mappedTile = imageColorMapper.map(tile, tileColors);
        char character = characterRecognizer.recognize(mappedTile, rgbColorFactory.toARGB(tileColors[1]));

        return characterEncoder.encode(character, tileColors[1], tileColors[0]);
    }

    private BiFunction<Integer, Integer, String> curryProcessTile(BufferedImage image, int tileWidth, int tileHeight) {
        return (x, y) -> processTile(image, tileWidth, tileHeight, x, y);
    }

}
