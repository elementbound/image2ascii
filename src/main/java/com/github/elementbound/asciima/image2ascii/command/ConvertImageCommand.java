package com.github.elementbound.asciima.image2ascii.command;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

@Component
@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements ConsoleCommand {
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

                System.out.print(characterEncoder.encode(character, tileColors[1], tileColors[0]));
            }

            System.out.println("\u001B[0m");
        }

        return 0;
    }


}
