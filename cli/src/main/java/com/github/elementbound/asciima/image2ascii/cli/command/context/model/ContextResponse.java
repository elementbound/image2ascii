package com.github.elementbound.asciima.image2ascii.cli.command.context.model;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterRecognizer;
import com.github.elementbound.asciima.image2ascii.colors.finder.PrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;

public class ContextResponse {
    private final PrimaryColorFinder palettePrimaryColorFinder;
    private final ImageColorMapper imageColorMapper;
    private final CharacterRecognizer characterRecognizer;
    private final CharacterEncoder characterEncoder;

    private ContextResponse(Builder builder) {
        this.palettePrimaryColorFinder = builder.palettePrimaryColorFinder;
        this.imageColorMapper = builder.imageColorMapper;
        this.characterRecognizer = builder.characterRecognizer;
        this.characterEncoder = builder.characterEncoder;
    }

    public PrimaryColorFinder getPalettePrimaryColorFinder() {
        return palettePrimaryColorFinder;
    }

    public ImageColorMapper getImageColorMapper() {
        return imageColorMapper;
    }

    public CharacterRecognizer getCharacterRecognizer() {
        return characterRecognizer;
    }

    public CharacterEncoder getCharacterEncoder() {
        return characterEncoder;
    }

    /**
     * Creates builder to build {@link ContextResponse}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link ContextResponse}.
     */
    public static final class Builder {
        private PrimaryColorFinder palettePrimaryColorFinder;
        private ImageColorMapper imageColorMapper;
        private CharacterRecognizer characterRecognizer;
        private CharacterEncoder characterEncoder;

        /**
         * Builder method for palettePrimaryColorFinder parameter.
         *
         * @param palettePrimaryColorFinder palettePrimaryColorFinder
         * @return builder
         */
        public Builder palettePrimaryColorFinder(PrimaryColorFinder palettePrimaryColorFinder) {
            this.palettePrimaryColorFinder = palettePrimaryColorFinder;
            return this;
        }

        /**
         * Builder method for imageColorMapper parameter.
         *
         * @param imageColorMapper imageColorMapper
         * @return builder
         */
        public Builder imageColorMapper(ImageColorMapper imageColorMapper) {
            this.imageColorMapper = imageColorMapper;
            return this;
        }

        /**
         * Builder method for characterRecognizer parameter.
         *
         * @param characterRecognizer characterRecognizer
         * @return builder
         */
        public Builder characterRecognizer(CharacterRecognizer characterRecognizer) {
            this.characterRecognizer = characterRecognizer;
            return this;
        }

        /**
         * Builder method for characterEncoder parameter.
         *
         * @param characterEncoder characterEncoder
         * @return builder
         */
        public Builder characterEncoder(CharacterEncoder characterEncoder) {
            this.characterEncoder = characterEncoder;
            return this;
        }

        /**
         * Builder method of the builder.
         *
         * @return built class
         */
        public ContextResponse build() {
            return new ContextResponse(this);
        }
    }
}
