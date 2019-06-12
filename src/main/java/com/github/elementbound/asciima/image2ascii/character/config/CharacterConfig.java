package com.github.elementbound.asciima.image2ascii.character.config;

import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;
import com.github.elementbound.asciima.image2ascii.character.renderer.impl.CharacterRendererImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Font;

@Configuration
public class CharacterConfig {
    @Bean
    public Font defaultFont() {
        return new Font("Courier New", Font.PLAIN, 12);
    }

    @Bean
    public CharacterRenderer characterRenderer() {
        return new CharacterRendererImpl(defaultFont());
    }
}
