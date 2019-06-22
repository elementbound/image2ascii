package com.github.elementbound.asciima.image2ascii.grid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cell<T> {
    private final int x;
    private final int y;
    private final T value;

    public Cell(int x, int y, T value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getValue() {
        return value;
    }

    public static <T> Cell<T> of(Cell<T> cell, T value) {
        return new Cell<>(
                cell.getX(),
                cell.getY(),
                value
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell<?> cell = (Cell<?>) o;

        return new EqualsBuilder()
                .append(x, cell.x)
                .append(y, cell.y)
                .append(value, cell.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(x)
                .append(y)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .append("value", value)
                .toString();
    }
}
