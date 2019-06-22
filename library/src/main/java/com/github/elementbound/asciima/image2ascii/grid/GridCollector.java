package com.github.elementbound.asciima.image2ascii.grid;

import com.github.elementbound.asciima.image2ascii.grid.impl.ArrayGrid;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

class GridCollector {
    static <T> Collector<Cell<T>, Set<Cell<T>>, Grid<T>> getCellCollector() {
        return Collector.of(
                HashSet::new,
                Set::add,
                (cells, cells2) -> {
                    Set<Cell<T>> result = new HashSet<>(cells.size() + cells2.size());
                    result.addAll(cells);
                    result.addAll(cells2);
                    return result;
                },
                cells -> {
                    int width = cells.stream().mapToInt(Cell::getX).max().orElse(-1) + 1;
                    int height = cells.stream().mapToInt(Cell::getY).max().orElse(-1) + 1;

                    Grid<T> result = new ArrayGrid<>(width, height);
                    cells.forEach(cell -> result.put(cell.getX(), cell.getY(), cell.getValue()));

                    return result;
                },
                Collector.Characteristics.UNORDERED,
                Collector.Characteristics.CONCURRENT
        );
    }
}
