package com.github.elementbound.asciima.image2ascii.colors.mapper;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.List;

@FunctionalInterface
public interface ColorMapperFactory {
    ColorMapper get(List<RGBColor> colors);
}
