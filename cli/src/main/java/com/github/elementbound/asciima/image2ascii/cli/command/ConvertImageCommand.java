package com.github.elementbound.asciima.image2ascii.cli.command;

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

        String result = String.join("", convertedImage.cells().stream()
                .map(cell -> Cell.of(cell, cell.getValue().getCharacter()))
                .map(cell -> Cell.of(cell, String.valueOf(cell.getValue())))
                .map(cell -> Cell.of(cell, cell.getValue() + ((cell.getX() == convertedImage.getWidth() - 1) ? "\n" : "")))
                .collect(Grid.collector()).values());

        System.out.println(result);

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
}
