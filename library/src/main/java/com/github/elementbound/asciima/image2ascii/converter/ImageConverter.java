package com.github.elementbound.asciima.image2ascii.converter;

import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;
import com.github.elementbound.asciima.image2ascii.grid.Grid;

import java.awt.image.BufferedImage;

public interface ImageConverter {
    Grid<CharacterCell> convert(BufferedImage image, ImageConverterConfiguration configuration);
}
