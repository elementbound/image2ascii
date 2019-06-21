package com.github.elementbound.asciima.image2ascii.colors.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HSVColor {
    private final float hue;
    private final float saturation;
    private final float value;

    public HSVColor(float hue, float saturation, float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HSVColor hsvColor = (HSVColor) o;

        return new EqualsBuilder()
                .append(hue, hsvColor.hue)
                .append(saturation, hsvColor.saturation)
                .append(value, hsvColor.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(hue)
                .append(saturation)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("hue", hue)
                .append("saturation", saturation)
                .append("value", value)
                .toString();
    }
}
