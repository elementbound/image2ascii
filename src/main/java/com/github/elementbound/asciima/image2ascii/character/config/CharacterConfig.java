package com.github.elementbound.asciima.image2ascii.character.config;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.encoder.impl.BasicPaletteCharacterEncoderImpl;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.CharacterRecognizerImpl;
import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.GradientCharacterWeightFunctionImpl;
import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;
import com.github.elementbound.asciima.image2ascii.character.renderer.impl.CharacterRendererImpl;

@Configuration
public class CharacterConfig {
    private static final String CHARACTER_GRADIENT = " .:-=+*#%@";
    private static final String CHARACTER_ALL = CHARACTER_GRADIENT;

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
        return new CharacterRecognizerImpl(RECOGNIZED_CHARACTERS, characterRenderer(), new GradientCharacterWeightFunctionImpl());
    }

    @Bean
    public CharacterEncoder characterEncoder() {
        return new BasicPaletteCharacterEncoderImpl();
    }
}
