package com.github.elementbound.asciima.image2ascii.character.recognizer.impl;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HitMissCharacterWeightFunctionImpl implements CharacterWeightFunction {
    @Override
    public double getWeight(BufferedImage sourceImage, int targetColor, BufferedImage characterImage) {
        int hits = 0;
        int misses = 0;
        double pixelCount = sourceImage.getWidth() * sourceImage.getHeight();

        for (int y = 0; y < sourceImage.getHeight(); y++) {
            for (int x = 0; x < sourceImage.getWidth(); x++) {
                int color = sourceImage.getRGB(x, y);

                float u = x / (float) sourceImage.getWidth();
                float v = y / (float) sourceImage.getHeight();
                int remappedX = (int) (u * characterImage.getWidth());
                int remappedY = (int) (v * characterImage.getHeight());

                int characterColor = characterImage.getRGB(remappedX, remappedY);

                boolean isColorTarget = color == targetColor;
                boolean isCharacterHit = characterColor != Color.BLACK.getRGB();

                hits += (isColorTarget == isCharacterHit) ? 1 : 0;
                misses += (isColorTarget == isCharacterHit) ? 0 : 1;
            }
        }

        return (hits - misses) / pixelCount;
    }
}
