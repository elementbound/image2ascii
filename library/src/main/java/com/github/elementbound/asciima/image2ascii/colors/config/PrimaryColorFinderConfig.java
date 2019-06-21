package com.github.elementbound.asciima.image2ascii.colors.config;

import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
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
}
