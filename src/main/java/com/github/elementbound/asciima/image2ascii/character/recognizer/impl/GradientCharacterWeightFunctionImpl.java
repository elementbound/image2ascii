package com.github.elementbound.asciima.image2ascii.character.recognizer.impl;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;

public class GradientCharacterWeightFunctionImpl implements CharacterWeightFunction {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradientCharacterWeightFunctionImpl.class);

    @Override
    public double getWeight(BufferedImage sourceImage, int targetColor, BufferedImage characterImage) {
        int characterBrights = 0;
        int sourceBrights = 0;

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

                sourceBrights += isColorTarget ? 1 : 0;
                characterBrights += isCharacterHit ? 1 : 0;
            }
        }

        // LOGGER.info("sourceBrights={}; characterBrights={}", sourceBrights, characterBrights);
        return 1.0 / (1.0 + Math.abs(sourceBrights - characterBrights));
    }
}
