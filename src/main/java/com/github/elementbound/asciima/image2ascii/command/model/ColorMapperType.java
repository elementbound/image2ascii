package com.github.elementbound.asciima.image2ascii.command.model;

import java.util.function.Function;

import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.mapper.impl.FuzzyColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.mapper.impl.NearestColorMapperFactory;

public enum ColorMapperType {
    NEAREST(NearestColorMapperFactory::new),
    FUZZY(FuzzyColorMapperFactory::new);

    private final Function<ColorDistanceFunction, ColorMapperFactory> factorySupplier;

    ColorMapperType(Function<ColorDistanceFunction, ColorMapperFactory> factorySupplier) {
        this.factorySupplier = factorySupplier;
    }

    public Function<ColorDistanceFunction, ColorMapperFactory> getFactorySupplier() {
        return factorySupplier;
    }
}
