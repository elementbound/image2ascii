package com.github.elementbound.asciima.image2ascii.colors.config.palette;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ascii16Palette {
    public static List<RGBColor> colors = Stream.of(
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
}
