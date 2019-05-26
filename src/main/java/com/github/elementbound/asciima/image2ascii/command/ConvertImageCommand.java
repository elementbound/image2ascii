package com.github.elementbound.asciima.image2ascii.command;

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

    @Parameters(index = "1", description = "Output file", paramLabel = "output")
    private Path outputPath;

    @Override
    public Integer call() throws IOException {
        BufferedImage image = ImageIO.read(inputPath.toFile());

        int tileWidth = 6;
        int tileHeight = 10;

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;

        List<BufferedImage> tiles = IntStream.range(0, tileCountX * tileCountY)
                .mapToObj(i -> new Point2(i % tileCountX, i / tileCountX))
                .map(point -> image.getSubimage(point.getX() * tileWidth, point.getY() * tileHeight, tileWidth, tileHeight))
                .collect(Collectors.toList());

        return 0;
    }
}
