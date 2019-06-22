package com.github.elementbound.asciima.image2ascii.cli.command;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.cli.command.model.CharacterSet;
import com.github.elementbound.asciima.image2ascii.cli.command.model.CharacterWeightFunctionType;
import com.github.elementbound.asciima.image2ascii.cli.command.model.ColorMapperType;
import com.github.elementbound.asciima.image2ascii.cli.command.model.Palette;
import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.converter.ImageConverter;
import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;
import com.github.elementbound.asciima.image2ascii.grid.Cell;
import com.github.elementbound.asciima.image2ascii.grid.Grid;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

@Component
@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements ConsoleCommand {
    private static final String POSSIBLE_VALUES = "Possible values: ${COMPLETION-CANDIDATES}";

    private static final String RESET_STYLE = "\u001B[0m";
    private static final String RESET_NEWLINE = RESET_STYLE + "\n";

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

    private final ColorDistanceFunction defaultColorDistance;
    private final ImageConverter imageConverter;

    public ConvertImageCommand(ColorDistanceFunction defaultColorDistance, ImageConverter imageConverter) {
        this.defaultColorDistance = defaultColorDistance;
        this.imageConverter = imageConverter;
    }

    @Override
    public Integer call() throws IOException {
        BufferedImage image = ImageIO.read(inputPath.toFile());

        Grid<CharacterCell> convertedImage = imageConverter.convert(image, buildConfiguration());

        StringBuilder result = new StringBuilder();

        String formatString = "";
        CharacterEncoder characterEncoder = palette.getCharacterEncoder();
        for (int y = 0; y < convertedImage.getHeight(); y++) {
            for (int x = 0; x < convertedImage.getWidth(); x++) {
                CharacterCell characterCell = convertedImage.get(x, y);

                String newFormatString = characterEncoder.getFormatString(characterCell);
                if (!formatString.equals(newFormatString)) {
                    formatString = newFormatString;
                    result.append(characterEncoder.encode(characterCell));
                } else {
                    result.append(characterCell.getCharacter());
                }
            }

            result.append(RESET_NEWLINE);
            result.append(formatString);
        }

        System.out.print(result.toString() + RESET_NEWLINE);

        return 0;
    }

    private ImageConverterConfiguration buildConfiguration() {
        return ImageConverterConfiguration.builder()
                .characterSet(characterSet.getCharacters())
                .characterWeightFunction(characterWeightFunctionType.getCharacterWeightFunctionSupplier().get())
                .colorDistanceFunction(defaultColorDistance)
                .colorMapperFactory(colorMapperType.getFactorySupplier().apply(defaultColorDistance))
                .font(new Font("Courier New", Font.PLAIN, 12))
                .palette(palette.getPalette())
                .build();
    }

    private String encodeCharacter(CharacterCell cell, CharacterEncoder characterEncoder) {
        return characterEncoder.encode(cell);
    }

    private Cell<String> appendNewline(Cell<String> cell, int lineLength) {
        String result = cell.getValue();

        if (cell.getX() + 1 == lineLength) {
            result += RESET_NEWLINE;
        }

        return Cell.of(cell, result);
    }
}
