package com.github.elementbound.asciima.image2ascii.converter.model;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;
import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.awt.Font;
import java.util.List;

public class ImageConverterConfiguration {
    private final List<RGBColor> palette;
    private final ColorMapperFactory colorMapperFactory;
    private final String characterSet;
    private final Font font;
    private final CharacterWeightFunction characterWeightFunction;
    private final ColorDistanceFunction colorDistanceFunction;

    public ImageConverterConfiguration(Builder builder) {
        this.palette = builder.palette;
        this.colorMapperFactory = builder.colorMapperFactory;
        this.characterSet = builder.characterSet;
        this.font = builder.font;
        this.characterWeightFunction = builder.characterWeightFunction;
        this.colorDistanceFunction = builder.colorDistanceFunction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<RGBColor> getPalette() {
        return palette;
    }

    public ColorMapperFactory getColorMapperFactory() {
        return colorMapperFactory;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public Font getFont() {
        return font;
    }

    public CharacterWeightFunction getCharacterWeightFunction() {
        return characterWeightFunction;
    }

    public ColorDistanceFunction getColorDistanceFunction() {
        return colorDistanceFunction;
    }

    public static class Builder {
        private List<RGBColor> palette;
        private ColorMapperFactory colorMapperFactory;
        private String characterSet;
        private Font font;
        private CharacterWeightFunction characterWeightFunction;
        private ColorDistanceFunction colorDistanceFunction;

        public Builder palette(List<RGBColor> palette) {
            this.palette = palette;
            return this;
        }

        public Builder colorMapperFactory(ColorMapperFactory colorMapperFactory) {
            this.colorMapperFactory = colorMapperFactory;
            return this;
        }

        public Builder characterSet(String characterSet) {
            this.characterSet = characterSet;
            return this;
        }

        public Builder font(Font font) {
            this.font = font;
            return this;
        }

        public Builder characterWeightFunction(CharacterWeightFunction characterWeightFunction) {
            this.characterWeightFunction = characterWeightFunction;
            return this;
        }

        public Builder colorDistanceFunction(ColorDistanceFunction colorDistanceFunction) {
            this.colorDistanceFunction = colorDistanceFunction;
            return this;
        }

        public ImageConverterConfiguration build() {
            return new ImageConverterConfiguration(this);
        }
    }
}
