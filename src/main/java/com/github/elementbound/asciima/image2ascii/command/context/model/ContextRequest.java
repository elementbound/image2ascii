package com.github.elementbound.asciima.image2ascii.command.context.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.elementbound.asciima.image2ascii.command.model.CharacterSet;
import com.github.elementbound.asciima.image2ascii.command.model.CharacterWeightFunctionType;
import com.github.elementbound.asciima.image2ascii.command.model.ColorMapperType;
import com.github.elementbound.asciima.image2ascii.command.model.Palette;

public class ContextRequest {
    private final CharacterSet characterSet;
    private final CharacterWeightFunctionType characterWeightFunctionType;
    private final ColorMapperType colorMapperType;
    private final Palette palette;

    private ContextRequest(Builder builder) {
        this.characterSet = builder.characterSet;
        this.characterWeightFunctionType = builder.characterWeightFunctionType;
        this.colorMapperType = builder.colorMapperType;
        this.palette = builder.palette;
    }

    public CharacterSet getCharacterSet() {
        return characterSet;
    }

    public CharacterWeightFunctionType getCharacterWeightFunctionType() {
        return characterWeightFunctionType;
    }

    public ColorMapperType getColorMapperType() {
        return colorMapperType;
    }

    public Palette getPalette() {
        return palette;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ContextRequest))
            return false;

        ContextRequest that = (ContextRequest) o;

        return new EqualsBuilder()
            .append(characterSet, that.characterSet)
            .append(characterWeightFunctionType, that.characterWeightFunctionType)
            .append(colorMapperType, that.colorMapperType)
            .append(palette, that.palette)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(characterSet)
            .append(characterWeightFunctionType)
            .append(colorMapperType)
            .append(palette)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("characterSet", characterSet)
            .append("characterWeightFunctionType", characterWeightFunctionType)
            .append("colorMapperType", colorMapperType)
            .append("palette", palette)
            .toString();
    }

    /**
     * Creates builder to build {@link ContextRequest}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link ContextRequest}.
     */
    public static final class Builder {
        private CharacterSet characterSet;
        private CharacterWeightFunctionType characterWeightFunctionType;
        private ColorMapperType colorMapperType;
        private Palette palette;

        /**
         * Builder method for characterSet parameter.
         *
         * @param characterSet characterSet
         * @return builder
         */
        public Builder characterSet(CharacterSet characterSet) {
            this.characterSet = characterSet;
            return this;
        }

        /**
         * Builder method for characterWeightFunctionType parameter.
         *
         * @param characterWeightFunctionType characterWeightFunctionType
         * @return builder
         */
        public Builder characterWeightFunctionType(CharacterWeightFunctionType characterWeightFunctionType) {
            this.characterWeightFunctionType = characterWeightFunctionType;
            return this;
        }

        /**
         * Builder method for colorMapperType parameter.
         *
         * @param colorMapperType colorMapperType
         * @return builder
         */
        public Builder colorMapperType(ColorMapperType colorMapperType) {
            this.colorMapperType = colorMapperType;
            return this;
        }

        /**
         * Builder method for palette parameter.
         *
         * @param palette palette
         * @return builder
         */
        public Builder palette(Palette palette) {
            this.palette = palette;
            return this;
        }

        /**
         * Builder method of the builder.
         *
         * @return built class
         */
        public ContextRequest build() {
            return new ContextRequest(this);
        }
    }
}
