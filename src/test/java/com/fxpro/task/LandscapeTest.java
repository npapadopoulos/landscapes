package com.fxpro.task;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static com.fxpro.task.Constants.MAX_HEIGHT;
import static com.fxpro.task.Constants.MAX_POSITION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LandscapeTest {

    @Test
    void getValidationErrorEmptyPositions() {
        var landscape = new Landscape.Builder(new int[]{}).build();
        assertThat(landscape.getValidationError(), is(Optional.of("\nInvalid input, positions cannot be 0.\n")));
    }

    @Test
    void getValidationErrorGreaterThanMaxPositions() {
        var landscape = new Landscape.Builder(IntStream.range(0, MAX_POSITION + 1).toArray()).build();
        assertThat(landscape.getValidationError(), is(Optional.of(String.format("\nInvalid input, positions cannot be more than %d.\n", MAX_POSITION))));
    }

    @Test
    void getValidationErrorNegativeHeight() {
        var landscape = new Landscape.Builder(new int[]{5, 2, 3, 4, 5, 4, 0, 3, -1}).build();
        assertThat(landscape.getValidationError(), is(Optional.of(String.format("\nInvalid input, position height cannot be negative or greater than %d.\n", MAX_HEIGHT))));
    }

    @Test
    void getValidationErrorGreaterThanMaxHeight() {
        var landscape = new Landscape.Builder(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1000000}).build();
        assertThat(landscape.getValidationError(), is(Optional.of(String.format("\nInvalid input, position height cannot be negative or greater than %d.\n", MAX_HEIGHT))));
    }

    @Test
    void getValidationErrorEmpty() {
        var landscape = new Landscape.Builder(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}).build();
        assertThat(landscape.getValidationError(), is(Optional.empty()));
    }
}