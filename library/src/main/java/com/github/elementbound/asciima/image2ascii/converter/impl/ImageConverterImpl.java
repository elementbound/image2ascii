package com.github.elementbound.asciima.image2ascii.converter.impl;

import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.converter.ImageConverter;
import com.github.elementbound.asciima.image2ascii.converter.factory.ImageConverterContextFactory;
import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterContext;
import com.github.elementbound.asciima.image2ascii.font.FontSize;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ImageConverterImpl implements ImageConverter {
    private final RGBColorFactory rgbColorFactory;
    private final ImageConverterContextFactory contextFactory;

    public ImageConverterImpl(RGBColorFactory rgbColorFactory, ImageConverterContextFactory contextFactory) {
        this.rgbColorFactory = rgbColorFactory;
        this.contextFactory = contextFactory;
    }

    @Override
    public List<CharacterCell> convert(BufferedImage image, ImageConverterConfiguration configuration) {
        ImageConverterContext context = contextFactory.createContext(configuration);
        FontSize fontSize = new FontSize(configuration.getFont());

        int tileWidth = fontSize.getWidth();
        int tileHeight = fontSize.getHeight();

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;

        BiFunction<Integer, Integer, CharacterCell> tileProcessor = curryProcessTile(image, context, tileWidth, tileHeight);

        return IntStream.range(0, tileCountY)
                .mapToObj(row -> ImmutablePair.of(row, IntStream.range(0, tileCountX)))
                .flatMap(pair -> pair.getRight().mapToObj(x -> tileProcessor.apply(x, pair.getLeft())))
                .collect(Collectors.toList());
    }

    private CharacterCell processTile(BufferedImage image, ImageConverterContext context, int tileWidth, int tileHeight, int x, int y) {
        BufferedImage tile = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        RGBColor[] tileColors = context.getPrimaryColorFinder().findPrimaryColors(tile, 2).toArray(new RGBColor[2]);
        BufferedImage mappedTile = context.getImageColorMapper().map(tile, tileColors);
        char character = context.getCharacterRecognizer().recognize(mappedTile, rgbColorFactory.toARGB(tileColors[1]));

        int backgroundArgb = rgbColorFactory.toARGB(tileColors[0]);
        int foregroundArgb = rgbColorFactory.toARGB(tileColors[1]);

        return new CharacterCell(backgroundArgb, foregroundArgb, character);
    }

    private BiFunction<Integer, Integer, CharacterCell> curryProcessTile(BufferedImage image, ImageConverterContext context, int tileWidth, int tileHeight) {
        return (x, y) -> processTile(image, context, tileWidth, tileHeight, x, y);
    }
}
