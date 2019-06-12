package com.github.elementbound.asciima.image2ascii.character.recognizer;

import java.awt.image.BufferedImage;

@FunctionalInterface
public interface CharacterWeightFunction {
    /**
     * Return the character weight for a given tile. The bigger the value, the stronger the match.
     *
     * @param sourceImage tile from the source
     * @param targetColor color to recognize
     * @param characterImage rendered character to recognize
     * @return character weight
     */
    double getWeight(BufferedImage sourceImage, int targetColor, BufferedImage characterImage);
}
