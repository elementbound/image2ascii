package com.github.elementbound.asciima.image2ascii.colors.mapper.impl;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapper;

import java.util.List;

public class NearestColorMapperImpl implements ColorMapper {
    private final ColorDistanceFunction colorDistanceFunction;
    private final List<RGBColor> colors;

    public NearestColorMapperImpl(ColorDistanceFunction colorDistanceFunction, List<RGBColor> colors) {
        this.colorDistanceFunction = colorDistanceFunction;
        this.colors = colors;
    }

    @Override
    public RGBColor map(RGBColor color) {
        return colors.stream()
                .min((o1, o2) -> (int) Math.signum(colorDistanceFunction.getDistance(color, o1) - colorDistanceFunction.getDistance(color, o2)))
                .orElse(color);
    }
}
