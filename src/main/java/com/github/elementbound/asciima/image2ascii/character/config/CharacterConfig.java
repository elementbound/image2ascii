package com.github.elementbound.asciima.image2ascii.character.config;

import com.github.elementbound.asciima.image2ascii.character.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.character.CharacterRenderer;
import com.github.elementbound.asciima.image2ascii.character.impl.CharacterRecognizerImpl;
import com.github.elementbound.asciima.image2ascii.character.impl.CharacterRendererImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Font;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class CharacterConfig {
    private static final String CHARACTER_GRADIENT = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
    private static final String CHARACTER_BORDERS = "|-_+/\\";

    private static final String CHARACTER_ALL = CHARACTER_BORDERS + CHARACTER_GRADIENT;

    private static final Set<Character> RECOGNIZED_CHARACTERS = IntStream.range(0, CHARACTER_ALL.length())
            .mapToObj(CHARACTER_ALL::charAt)
            .collect(Collectors.toSet());

    @Bean
    public Font defaultFont() {
        return new Font("Courier New", Font.PLAIN, 12);
    }

    @Bean
    public CharacterRenderer characterRenderer() {
        return new CharacterRendererImpl(defaultFont());
    }

    @Bean
    public CharacterRecognizer characterRecognizer() {
        return new CharacterRecognizerImpl(RECOGNIZED_CHARACTERS, characterRenderer());
    }
}
