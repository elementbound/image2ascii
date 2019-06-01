package com.github.elementbound.asciima.image2ascii.colors.mapper.impl;

import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapper;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.List;

public class NearestColorMapperFactory implements ColorMapperFactory {
    private final ColorDistanceFunction colorDistanceFunction;

    public NearestColorMapperFactory(ColorDistanceFunction colorDistanceFunction) {
        this.colorDistanceFunction = colorDistanceFunction;
    }

    @Override
    public ColorMapper get(List<RGBColor> colors) {
        return new NearestColorMapperImpl(colorDistanceFunction, colors);
    }
}
