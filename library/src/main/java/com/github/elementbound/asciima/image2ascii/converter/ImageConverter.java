package com.github.elementbound.asciima.image2ascii.converter;

import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageConverter {
    List<CharacterCell> convert(BufferedImage image, ImageConverterConfiguration configuration);
}
