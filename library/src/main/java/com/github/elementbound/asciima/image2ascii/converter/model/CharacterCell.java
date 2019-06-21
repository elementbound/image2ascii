package com.github.elementbound.asciima.image2ascii.converter.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CharacterCell {
    private final int backgroundColor;
    private final int foregroundColor;
    private final char character;

    public CharacterCell(int backgroundColor, int foregroundColor, char character) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.character = character;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getForegroundColor() {
        return foregroundColor;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CharacterCell that = (CharacterCell) o;

        return new EqualsBuilder()
                .append(backgroundColor, that.backgroundColor)
                .append(foregroundColor, that.foregroundColor)
                .append(character, that.character)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(backgroundColor)
                .append(foregroundColor)
                .append(character)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("backgroundColor", backgroundColor)
                .append("foregroundColor", foregroundColor)
                .append("character", character)
                .toString();
    }
}
