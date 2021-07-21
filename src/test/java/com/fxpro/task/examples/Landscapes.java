package com.fxpro.task.examples;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

public class Landscapes {
    private static final Map<String, Long> examples = Map.of(
            "5,2,3,4,5,4,0,3,1",
            9L,
            "1,2,5,2,5",
            3L,
            "0,1,0,2,1,0,3,2,1,2,1,",
            5L
    );

    private static Stream<Arguments> landscapes() {
        return examples.keySet().stream().map(Arguments::of);
    }

    public static Long getResult(String landscape) {
        return examples.get(landscape);
    }
}
