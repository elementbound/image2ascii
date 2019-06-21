package com.github.elementbound.asciima.image2ascii.image;

import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapper;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ImageColorMapper {
    private final ColorMapperFactory colorMapperFactory;
    private final RGBColorFactory rgbColorFactory;

    public ImageColorMapper(ColorMapperFactory colorMapperFactory, RGBColorFactory rgbColorFactory) {
        this.colorMapperFactory = colorMapperFactory;
        this.rgbColorFactory = rgbColorFactory;
    }

    public BufferedImage map(BufferedImage image, RGBColor[] colors) {
        int width = image.getWidth();
        int height = image.getHeight();
        ColorMapper colorMapper = colorMapperFactory.get(Arrays.asList(colors));

        BufferedImage result = new BufferedImage(width, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                RGBColor sourceColor = rgbColorFactory.fromARGB(image.getRGB(x, y));
                RGBColor resultColor = colorMapper.map(sourceColor);

                result.setRGB(x, y, rgbColorFactory.toARGB(resultColor));
            }
        }

        return result;
    }
}
