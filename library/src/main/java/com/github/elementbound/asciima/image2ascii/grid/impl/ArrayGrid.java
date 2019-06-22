package com.github.elementbound.asciima.image2ascii.grid.impl;

import com.github.elementbound.asciima.image2ascii.grid.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ArrayGrid<T> extends AbstractGrid<T> {
    private final int width;
    private final int height;
    private final T[] values;

    public ArrayGrid(int width, int height) {
        this.width = width;
        this.height = height;

        this.values = (T[]) new Object[size()];
    }

    @Override
    public T get(int x, int y) {
        validateRange(x, y);
        return values[hash(x, y)];
    }

    @Override
    public T put(int x, int y, T value) {
        validateRange(x, y);

        values[hash(x, y)] = value;
        return value;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int size() {
        return width * height;
    }

    @Override
    public Set<Cell<T>> cells() {
        Set<Cell<T>> result = new HashSet<>(size());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.add(new Cell<>(x, y, values[hash(x, y)]));
            }
        }

        return result;
    }

    @Override
    public Collection<T> values() {
        Collection<T> result = new ArrayList<>(size());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.add(values[hash(x, y)]);
            }
        }

        return result;
    }

    private int hash(int x, int y) {
        return y * width + x;
    }

    private void validateRange(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Grid index out of bounds: [" + x + "; " + y + "]");
        }
    }
}
