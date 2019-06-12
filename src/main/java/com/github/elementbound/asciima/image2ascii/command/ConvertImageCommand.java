package com.github.elementbound.asciima.image2ascii.command;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Component
@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements ConsoleCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertImageCommand.class);

    @Parameters(index = "0", description = "Input file", paramLabel = "input")
    private Path inputPath;

    private final PrimaryColorFinder asciiPalettePrimaryColorFinder;
    private final ImageColorMapper imageColorMapper;
    private final CharacterRecognizer characterRecognizer;
    private final RGBColorFactory rgbColorFactory;
    private final CharacterEncoder characterEncoder;

    public ConvertImageCommand(PrimaryColorFinder asciiPalettePrimaryColorFinder, ImageColorMapper imageColorMapper, CharacterRecognizer characterRecognizer, RGBColorFactory rgbColorFactory, CharacterEncoder characterEncoder) {
        this.asciiPalettePrimaryColorFinder = asciiPalettePrimaryColorFinder;
        this.imageColorMapper = imageColorMapper;
        this.characterRecognizer = characterRecognizer;
        this.rgbColorFactory = rgbColorFactory;
        this.characterEncoder = characterEncoder;
    }

    @Override
    public Integer call() throws IOException {
        BufferedImage image = ImageIO.read(inputPath.toFile());
        StringBuilder result = new StringBuilder();

        int tileWidth = 6;
        int tileHeight = 10;

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;

        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                BufferedImage tile = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                RGBColor[] tileColors = asciiPalettePrimaryColorFinder.findPrimaryColors(tile, 2).toArray(new RGBColor[2]);
                BufferedImage mappedTile = imageColorMapper.map(tile, tileColors);
                char character = characterRecognizer.recognize(mappedTile, rgbColorFactory.toARGB(tileColors[1]));

                result.append(characterEncoder.encode(character, tileColors[1], tileColors[0]));
            }

            LOGGER.info("Progress: {}/{} = {}%", y, tileCountY, y*100 / tileCountY);
            result.append("\u001B[0m\n");
        }

        System.out.print(result.toString());

        return 0;
    }


}
