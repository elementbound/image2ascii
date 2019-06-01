package com.github.elementbound.asciima.image2ascii.colors;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

@FunctionalInterface
public interface ColorDistanceFunction {
    double getDistance(RGBColor a, RGBColor b);
}
