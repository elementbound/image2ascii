package com.github.elementbound.asciima.image2ascii.grid;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;

public interface Grid<T> {
    T get(int x, int y);

    T put(int x, int y, T value);

    int getWidth();

    int getHeight();

    int size();

    Set<Cell<T>> cells();

    Collection<T> values();

    static <T> Collector<Cell<T>, Set<Cell<T>>, Grid<T>> collector() {
        return GridCollector.getCellCollector();
    }
}
