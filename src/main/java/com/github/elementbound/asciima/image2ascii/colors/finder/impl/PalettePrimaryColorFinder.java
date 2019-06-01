package com.github.elementbound.asciima.image2ascii.colors.finder.impl;

import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.elementbound.asciima.image2ascii.image.ImageIterator.asIterable;

public class PalettePrimaryColorFinder implements PrimaryColorFinder {
    private final RGBColorFactory rgbColorFactory;
    private final List<RGBColor> palette;
    private final ColorDistanceFunction colorDistanceFunction;

    public PalettePrimaryColorFinder(RGBColorFactory rgbColorFactory, List<RGBColor> palette, ColorDistanceFunction colorDistanceFunction) {
        this.rgbColorFactory = rgbColorFactory;
        this.palette = palette;
        this.colorDistanceFunction = colorDistanceFunction;
    }

    @Override
    public List<RGBColor> findPrimaryColors(BufferedImage image, int count) {
        Map<RGBColor, Double> paletteColorWeights = palette.stream()
                .collect(Collectors.toMap(color -> color, value -> 0.0));

        for (RGBColor paletteColor : palette) {
            for (int argb : asIterable(image)) {
                RGBColor color = rgbColorFactory.fromARGB(argb);

                double distance = colorDistanceFunction.getDistance(paletteColor, color);
                double weight = paletteColorWeights.get(paletteColor) + distance;

                paletteColorWeights.put(paletteColor, weight);
            }
        }

        return paletteColorWeights.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(count)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
