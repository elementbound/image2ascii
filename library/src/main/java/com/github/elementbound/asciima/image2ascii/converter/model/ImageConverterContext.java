package com.github.elementbound.asciima.image2ascii.converter.model;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;

public class ImageConverterContext {
    private final PrimaryColorFinder primaryColorFinder;
    private final ImageColorMapper imageColorMapper;
    private final CharacterRecognizer characterRecognizer;

    public ImageConverterContext(Builder builder) {
        this.primaryColorFinder = builder.primaryColorFinder;
        this.imageColorMapper = builder.imageColorMapper;
        this.characterRecognizer = builder.characterRecognizer;
    }

    public PrimaryColorFinder getPrimaryColorFinder() {
        return primaryColorFinder;
    }

    public ImageColorMapper getImageColorMapper() {
        return imageColorMapper;
    }

    public CharacterRecognizer getCharacterRecognizer() {
        return characterRecognizer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PrimaryColorFinder primaryColorFinder;
        private ImageColorMapper imageColorMapper;
        private CharacterRecognizer characterRecognizer;

        public Builder primaryColorFinder(PrimaryColorFinder primaryColorFinder) {
            this.primaryColorFinder = primaryColorFinder;
            return this;
        }

        public Builder imageColorMapper(ImageColorMapper imageColorMapper) {
            this.imageColorMapper = imageColorMapper;
            return this;
        }

        public Builder characterRecognizer(CharacterRecognizer characterRecognizer) {
            this.characterRecognizer = characterRecognizer;
            return this;
        }

        public ImageConverterContext build() {
            return new ImageConverterContext(this);
        }
    }
}
