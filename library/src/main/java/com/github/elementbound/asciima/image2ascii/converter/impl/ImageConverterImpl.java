package com.github.elementbound.asciima.image2ascii.converter.impl;

import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.converter.ImageConverter;
import com.github.elementbound.asciima.image2ascii.converter.factory.ImageConverterContextFactory;
import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterContext;
import com.github.elementbound.asciima.image2ascii.font.FontSize;
import com.github.elementbound.asciima.image2ascii.grid.Cell;
import com.github.elementbound.asciima.image2ascii.grid.Grid;
import com.github.elementbound.asciima.image2ascii.grid.impl.ArrayGrid;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public class ImageConverterImpl implements ImageConverter {
    private final RGBColorFactory rgbColorFactory;
    private final ImageConverterContextFactory contextFactory;

    public ImageConverterImpl(RGBColorFactory rgbColorFactory, ImageConverterContextFactory contextFactory) {
        this.rgbColorFactory = rgbColorFactory;
        this.contextFactory = contextFactory;
    }

    @Override
    public Grid<CharacterCell> convert(BufferedImage image, ImageConverterConfiguration configuration) {
        ImageConverterContext context = contextFactory.createContext(configuration);
        FontSize fontSize = new FontSize(configuration.getFont());

        int tileWidth = fontSize.getWidth();
        int tileHeight = fontSize.getHeight();

        int tileCountX = image.getWidth() / tileWidth;
        int tileCountY = image.getHeight() / tileHeight;

        return new ArrayGrid<CharacterCell>(tileCountX, tileCountY).cells().stream()
                .map(cell -> Cell.of(cell, processTile(image, context, tileWidth, tileHeight, cell.getX(), cell.getY())))
                .collect(Grid.collector());
    }

    private CharacterCell processTile(BufferedImage image, ImageConverterContext context, int tileWidth, int tileHeight, int x, int y) {
        BufferedImage tile = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

        RGBColor[] tileColors = context.getPrimaryColorFinder().findPrimaryColors(tile, 2).toArray(new RGBColor[2]);
        RGBColor backgroundColor = tileColors[0];
        RGBColor foregroundColor = tileColors[1];

        BufferedImage mappedTile = context.getImageColorMapper().map(tile, tileColors);

        char character = context.getCharacterRecognizer().recognize(mappedTile, rgbColorFactory.toARGB(foregroundColor));

        return new CharacterCell(backgroundColor, foregroundColor, character);
    }
}
