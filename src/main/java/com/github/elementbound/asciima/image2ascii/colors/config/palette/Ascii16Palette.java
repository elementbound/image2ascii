package com.github.elementbound.asciima.image2ascii.colors.config.palette;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ascii16Palette {
    public static List<RGBColor> colors = Stream.of(
            new RGBColor(0 / 255.f, 0 / 255.f, 0 / 255.f),
            new RGBColor(170 / 255.f, 0 / 255.f, 0 / 255.f),
            new RGBColor(0 / 255.f, 170 / 255.f, 0 / 255.f),
            new RGBColor(170 / 255.f, 85 / 255.f, 0 / 255.f),
            new RGBColor(0 / 255.f, 0 / 255.f, 170 / 255.f),
            new RGBColor(170 / 255.f, 0 / 255.f, 170 / 255.f),
            new RGBColor(0 / 255.f, 170 / 255.f, 170 / 255.f),
            new RGBColor(170 / 255.f, 170 / 255.f, 170 / 255.f),
            new RGBColor(85 / 255.f, 85 / 255.f, 85 / 255.f),
            new RGBColor(255 / 255.f, 85 / 255.f, 85 / 255.f),
            new RGBColor(85 / 255.f, 255 / 255.f, 85 / 255.f),
            new RGBColor(255 / 255.f, 255 / 255.f, 85 / 255.f),
            new RGBColor(85 / 255.f, 85 / 255.f, 255 / 255.f),
            new RGBColor(255 / 255.f, 85 / 255.f, 255 / 255.f),
            new RGBColor(85 / 255.f, 255 / 255.f, 255 / 255.f),
            new RGBColor(255 / 255.f, 255 / 255.f, 255 / 255.f)
    ).collect(Collectors.toList());
}
