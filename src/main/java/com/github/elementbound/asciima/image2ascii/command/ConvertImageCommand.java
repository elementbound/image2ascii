package com.github.elementbound.asciima.image2ascii.command;

import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.util.Point2;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Command(name = "image2ascii", mixinStandardHelpOptions = true)
public class ConvertImageCommand implements ConsoleCommand {
    @Parameters(index = "0", description = "Input file", paramLabel = "input")
    private Path inputPath;

    private final PrimaryColorFinder asciiPalettePrimaryColorFinder;

    public ConvertImageCommand(PrimaryColorFinder asciiPalettePrimaryColorFinder) {
        this.asciiPalettePrimaryColorFinder = asciiPalettePrimaryColorFinder;
    }

    @Override
    public Integer call() throws IOException {
        BufferedImage image = ImageIO.read(inputPath.toFile());

        int tileWidth = 6;
        int tileHeight = 10;

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;

        List<RGBColor[]> primaryColorPerTile = IntStream.range(0, tileCountX * tileCountY)
                .mapToObj(i -> new Point2(i % tileCountX, i / tileCountX))
                .map(point -> image.getSubimage(point.getX() * tileWidth, point.getY() * tileHeight, tileWidth, tileHeight))
                .map(tile -> asciiPalettePrimaryColorFinder.findPrimaryColors(tile, 2))
                .map(primaryColors -> primaryColors.toArray(new RGBColor[2]))
                .collect(Collectors.toList());

        for (int y = 0; y < tileCountY; y++) {
            for (int x = 0; x < tileCountX; x++) {
                RGBColor[] tileColors = primaryColorPerTile.get(y * tileCountX + x);
                System.out.printf("\u001B[38;2;%d;%d;%dm\u001B[48;2;%d;%d;%dm#",
                        (int) (tileColors[1].getRed() * 255),
                        (int) (tileColors[1].getGreen() * 255),
                        (int) (tileColors[1].getBlue() * 255),

                        (int) (tileColors[0].getRed() * 255),
                        (int) (tileColors[0].getGreen() * 255),
                        (int) (tileColors[0].getBlue() * 255));
            }

            System.out.println("\u001B[0m");
        }

        return 0;
    }


}
