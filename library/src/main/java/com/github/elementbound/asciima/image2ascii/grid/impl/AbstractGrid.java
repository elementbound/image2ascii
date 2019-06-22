package com.github.elementbound.asciima.image2ascii.grid.impl;

import com.github.elementbound.asciima.image2ascii.grid.Grid;

public abstract class AbstractGrid<T> implements Grid<T> {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Grid<?> grid = (Grid<?>) o;

        if (this.getWidth() != grid.getWidth() ||
                this.getHeight() != grid.getHeight()) {
            return false;
        }

        return this.cells().equals(((Grid<?>) o).cells());
    }

    @Override
    public int hashCode() {
        return this.cells().hashCode();
    }
}
