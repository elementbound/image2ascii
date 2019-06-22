package com.github.elementbound.asciima.image2ascii.grid.impl;

import com.github.elementbound.asciima.image2ascii.grid.Cell;
import com.github.elementbound.asciima.image2ascii.grid.Grid;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayGridTest {
    @Test
    public void streamShouldCollect() {
        // Given
        Grid<Integer> grid = new ArrayGrid<>(2, 2);
        Grid<Integer> expected = new ArrayGrid<>(2, 2);

        expected.put(0, 0, 0);
        expected.put(1, 0, 1);
        expected.put(0, 1, 1);
        expected.put(1, 1, 2);

        // When
        Grid<Integer> actual = grid.cells().stream()
                .map(cell -> Cell.of(cell, cell.getX() + cell.getY()))
                .collect(Grid.collector());

        // Then
        assertThat(actual, is(expected));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void getShouldThrowIfOutOfRange() {
        // Given
        Grid<Integer> grid = new ArrayGrid<>(1, 1);

        // When
        grid.get(2, 2);

        // Then throw
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void putShouldThrowIfOutOfRange() {
        // Given
        Grid<Integer> grid = new ArrayGrid<>(0, 0);

        // When
        grid.put(2, 2, 1);

        // Then throw
    }
}