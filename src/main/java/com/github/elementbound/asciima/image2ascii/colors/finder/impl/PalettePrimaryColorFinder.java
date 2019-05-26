package com.github.elementbound.asciima.image2ascii.colors.finder.impl;

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

    public PalettePrimaryColorFinder(RGBColorFactory rgbColorFactory, List<RGBColor> palette) {
        this.rgbColorFactory = rgbColorFactory;
        this.palette = palette;
    }

    @Override
    public List<RGBColor> findPrimaryColors(BufferedImage image, int count) {
        Map<RGBColor, Double> paletteColorWeights = palette.stream()
                .collect(Collectors.toMap(color -> color, value -> 0.0));

        for (RGBColor paletteColor : palette) {
            for (int argb : asIterable(image)) {
                RGBColor color = rgbColorFactory.fromARGB(argb);

                double distance = getColorDistance(paletteColor, color);
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

    private double getColorDistance(RGBColor a, RGBColor b) {
        // Simple pythagorian distance for now
        RGBColor delta = new RGBColor(a.getRed() - b.getRed(), a.getGreen() - b.getGreen(), a.getBlue() - b.getBlue());

        return Math.sqrt(delta.getRed() * delta.getRed() +
                delta.getGreen() * delta.getGreen() +
                delta.getBlue() * delta.getBlue());
    }
}
