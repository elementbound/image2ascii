package com.github.elementbound.asciima.image2ascii.colors.config;

import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.config.palette.Ascii16Palette;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.finder.impl.PalettePrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.mapper.impl.FuzzyColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimaryColorFinderConfig {
    @Autowired
    private RGBColorFactory rgbColorFactory;

    @Bean
    public ColorDistanceFunction defaultColorDistance() {
        return (a, b) -> {
            RGBColor delta = new RGBColor(a.getRed() - b.getRed(), a.getGreen() - b.getGreen(), a.getBlue() - b.getBlue());

            return Math.sqrt(
                    2.0 * (delta.getRed() * delta.getRed()) +
                            4.0 * (delta.getGreen() * delta.getGreen()) +
                            3.0 * (delta.getBlue() * delta.getBlue()));
        };
    }

    @Bean
    public PrimaryColorFinder asciiPalettePrimaryColorFinder() {
        return new PalettePrimaryColorFinder(rgbColorFactory, Ascii16Palette.colors, defaultColorDistance());
    }

    @Bean
    public ColorMapperFactory colorMapperFactory() {
        return new FuzzyColorMapperFactory(defaultColorDistance());
    }
}
