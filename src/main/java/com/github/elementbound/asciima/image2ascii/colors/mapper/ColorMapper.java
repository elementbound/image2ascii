package com.github.elementbound.asciima.image2ascii.colors.mapper;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

@FunctionalInterface
public interface ColorMapper {
    RGBColor map(RGBColor color);
}
