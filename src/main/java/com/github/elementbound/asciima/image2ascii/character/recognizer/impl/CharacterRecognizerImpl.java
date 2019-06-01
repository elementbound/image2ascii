package com.github.elementbound.asciima.image2ascii.character.recognizer.impl;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterRecognizerImpl implements CharacterRecognizer {
    private final Map<Character, BufferedImage> characters;

    public CharacterRecognizerImpl(Set<Character> characters, CharacterRenderer characterRenderer) {
        this.characters = characters.stream()
                .collect(Collectors.toMap(Function.identity(), characterRenderer::render));
    }

    @Override
    public char recognize(BufferedImage image, int targetColor) {
        return characters.entrySet().stream()
                .map(entry -> createWeightEntry(entry.getKey(), getCharacterWeight(image, targetColor, entry.getValue())))
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(' ');
    }

    private double getCharacterWeight(BufferedImage image, int targetColor, BufferedImage characterImage) {
        int hits = 0;
        int misses = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);

                float u = x / (float) image.getWidth();
                float v = y / (float) image.getHeight();
                int remappedX = (int) (u * characterImage.getWidth());
                int remappedY = (int) (v * characterImage.getHeight());

                int characterColor = characterImage.getRGB(remappedX, remappedY);

                boolean isColorTarget = color == targetColor;
                boolean isCharacterHit = characterColor != Color.BLACK.getRGB();

                hits += (isColorTarget == isCharacterHit) ? 1 : 0;
                misses += (isColorTarget == isCharacterHit) ? 0 : 1;
            }
        }

        return hits - misses;
    }

    private Map.Entry<Character, Double> createWeightEntry(char character, double weight) {
        return new AbstractMap.SimpleImmutableEntry<>(character, weight);
    }
}
