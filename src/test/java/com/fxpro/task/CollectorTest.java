package com.fxpro.task;

import com.fxpro.task.examples.Landscapes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CollectorTest extends Landscapes {

    @ParameterizedTest
    @EmptySource
    void testEmptyLandscape(String landscape) {
        var result = new Collector() {
        }.calculateWaterAmount(new int[]{});
        assertThat(result, equalTo(0L));
    }

    @ParameterizedTest
    @MethodSource("landscapes")
    void testLandscapes(String landscape) {
        int[] positions = Arrays.stream(landscape.split(",")).mapToInt(Integer::valueOf).toArray();
        var result = new Collector() {
        }.calculateWaterAmount(positions);
        assertThat(result, equalTo(getResult(landscape)));
    }
}