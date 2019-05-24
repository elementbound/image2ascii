package com.github.elementbound.asciima.image2ascii.colors.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RGBColor {
    private final float red;
    private final float green;
    private final float blue;

    public RGBColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RGBColor rgbColor = (RGBColor) o;

        return new EqualsBuilder()
                .append(red, rgbColor.red)
                .append(green, rgbColor.green)
                .append(blue, rgbColor.blue)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(red)
                .append(green)
                .append(blue)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("red", red)
                .append("green", green)
                .append("blue", blue)
                .toString();
    }
}
