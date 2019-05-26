package com.github.elementbound.asciima.image2ascii.colors.config;

import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.finder.impl.PalettePrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class PrimaryColorFinderConfig {
    private static List<RGBColor> asciiPalette = Stream.of(
            new RGBColor(0.00f, 0.00f, 0.00f),
            new RGBColor(0.66f, 0.00f, 0.00f),
            new RGBColor(0.00f, 0.66f, 0.00f),
            new RGBColor(0.66f, 0.33f, 0.00f),
            new RGBColor(0.00f, 0.00f, 0.66f),
            new RGBColor(0.66f, 0.00f, 0.66f),
            new RGBColor(0.00f, 0.66f, 0.66f),
            new RGBColor(0.66f, 0.66f, 0.66f),
            new RGBColor(0.33f, 0.33f, 0.33f),
            new RGBColor(1.00f, 0.33f, 0.33f),
            new RGBColor(0.33f, 1.00f, 0.33f),
            new RGBColor(1.00f, 1.00f, 0.33f),
            new RGBColor(0.33f, 0.33f, 1.00f),
            new RGBColor(1.00f, 0.33f, 1.00f),
            new RGBColor(0.33f, 1.00f, 1.00f),
            new RGBColor(1.00f, 1.00f, 1.00f)
    ).collect(Collectors.toList());

    @Autowired
    private RGBColorFactory rgbColorFactory;

    @Bean
    public PrimaryColorFinder asciiPalettePrimaryColorFinder() {
        return new PalettePrimaryColorFinder(rgbColorFactory, asciiPalette);
    }
}