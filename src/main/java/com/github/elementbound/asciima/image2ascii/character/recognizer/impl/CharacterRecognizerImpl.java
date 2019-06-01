package com.github.elementbound.asciima.image2ascii.character.recognizer.impl;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;
import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;

import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterRecognizerImpl implements CharacterRecognizer {
    private final Map<Character, BufferedImage> characters;
    private final CharacterWeightFunction characterWeightFunction;

    public CharacterRecognizerImpl(Set<Character> characters, CharacterRenderer characterRenderer, CharacterWeightFunction characterWeightFunction) {
        this.characterWeightFunction = characterWeightFunction;

        this.characters = characters.stream()
                .collect(Collectors.toMap(Function.identity(), characterRenderer::render));
    }

    @Override
    public char recognize(BufferedImage image, int targetColor) {
        return characters.entrySet().stream()
                .map(entry -> createWeightEntry(entry.getKey(), characterWeightFunction.getWeight(image, targetColor, entry.getValue())))
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(' ');
    }

    private Map.Entry<Character, Double> createWeightEntry(char character, double weight) {
        return new AbstractMap.SimpleImmutableEntry<>(character, weight);
    }
}
