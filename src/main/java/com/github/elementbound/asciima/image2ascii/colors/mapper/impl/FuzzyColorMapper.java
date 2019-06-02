package com.github.elementbound.asciima.image2ascii.colors.mapper.impl;

import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapper;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FuzzyColorMapper implements ColorMapper {
    private final ColorDistanceFunction colorDistanceFunction;
    private final List<RGBColor> colors;
    private final Random random;

    public FuzzyColorMapper(ColorDistanceFunction colorDistanceFunction, List<RGBColor> colors) {
        this(colorDistanceFunction, colors, new Random());
    }

    public FuzzyColorMapper(ColorDistanceFunction colorDistanceFunction, List<RGBColor> colors, Random random) {
        this.colorDistanceFunction = colorDistanceFunction;
        this.colors = colors;
        this.random = random;
    }

    @Override
    public RGBColor map(RGBColor color) {
        Map<RGBColor, Double> colorWeights = new HashMap<>();
        for (RGBColor paletteColor : colors) {
            colorWeights.put(paletteColor, 1.0 / (1.0 + colorDistanceFunction.getDistance(paletteColor, color)));
        }

        double weightSum = colorWeights.values().stream()
                .mapToDouble(weight -> weight)
                .sum();

        double choice = this.random.nextDouble() * weightSum;

        List<Map.Entry<RGBColor, Double>> weightList = colorWeights.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        for (Map.Entry<RGBColor, Double> entry : weightList) {
            Double weight = entry.getValue();
            RGBColor colorCandidate = entry.getKey();

            if (choice < weight) {
                return colorCandidate;
            } else {
                choice -= weight;
            }
        }

        return color;
    }
}
