package com.fxpro.task;

import com.fxpro.task.examples.Landscapes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

import static com.fxpro.task.Constants.MAX_HEIGHT;
import static com.fxpro.task.Constants.MAX_POSITION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ApplicationTest extends Landscapes {

    private final ByteArrayOutputStream systemOutStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream systemErrStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(systemOutStream));
        System.setErr(new PrintStream(systemErrStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,2,3,4,5,4,0,3,10000001000000"})
    void testInvalidInputLandscape(String landscape) {
        var positions = landscape.split(",");
        Application.main(positions);
        assertThat(systemErrStream.toString().trim(), equalTo(String.format("Invalid input %s.", Arrays.toString(positions))));
        assertThat(systemOutStream.toString().trim(), equalTo(Constants.usage().trim()));
    }

    @ParameterizedTest
    @EmptySource
    void testEmptyLandscape(String landscape) {
        Application.main(new String[]{});
        assertThat(systemErrStream.toString().trim(), equalTo("Invalid input, positions cannot be 0."));
        assertThat(systemOutStream.toString().trim(), equalTo(Constants.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5,2,3,4,5,4,0,3,-1"})
    void testNegativeHeightLandscape(String landscape) {
        Application.main(landscape.split(","));
        assertThat(systemErrStream.toString().trim(), equalTo(String.format("Invalid input, position height cannot be negative or greater than %d.", MAX_HEIGHT)));
        assertThat(systemOutStream.toString().trim(), equalTo(Constants.usage().trim()));
    }


    @ParameterizedTest
    @ValueSource(strings = {"5,2,3,4,5,4,0,3,1000000"})
    void testMaxHeightLandscape(String landscape) {
        Application.main(landscape.split(","));
        assertThat(systemErrStream.toString().trim(), equalTo(String.format("Invalid input, position height cannot be negative or greater than %d.", MAX_HEIGHT)));
        assertThat(systemOutStream.toString().trim(), equalTo(Constants.usage().trim()));
    }

    @Test
    void testMaxPositionsLandscape() {
        String[] landscape = IntStream.range(0, MAX_POSITION + 1).mapToObj(String::valueOf).toArray(String[]::new);
        Application.main(landscape);
        assertThat(systemErrStream.toString().trim(), equalTo(String.format("Invalid input, positions cannot be more than %d.", MAX_POSITION)));
        assertThat(systemOutStream.toString().trim(), equalTo(Constants.usage().trim()));
    }

    @ParameterizedTest
    @MethodSource("landscapes")
    void testLandscapes(String landscape) {
        var positions = landscape.split(",");
        Application.main(positions);

        String output = String.format("Collected %d squares of water for landscape %s.", getResult(landscape), Arrays.toString(positions));

        assertThat(systemErrStream.toString().trim(), is(emptyString()));
        assertThat(systemOutStream.toString().trim(), equalTo(output));
    }
}