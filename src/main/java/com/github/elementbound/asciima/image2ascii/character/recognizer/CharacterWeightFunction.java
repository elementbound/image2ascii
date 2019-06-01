package com.github.elementbound.asciima.image2ascii.character.recognizer;

import java.awt.image.BufferedImage;

@FunctionalInterface
public interface CharacterWeightFunction {
    double getWeight(BufferedImage image, int targetColor, BufferedImage characterImage);
}
