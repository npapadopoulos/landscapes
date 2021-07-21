package com.fxpro.task;

import java.util.Arrays;

import static com.fxpro.task.Constants.usage;

public class Application {

    public static void main(String[] args) {
        try {
            var positions = Arrays.stream(args)
                    .mapToInt(Integer::valueOf)
                    .toArray();
            var landscape = new Landscape.Builder(positions).build();
            if (landscape.getValidationError().isEmpty()) {
                long result = new Collector() {}.calculateWaterAmount(landscape.getPositions());
                System.out.println(String.format("\nCollected %d squares of water for landscape %s.\n", result, Arrays.toString(args)));
                return;
            }

            System.err.println(landscape.getValidationError().get());
            System.out.println(usage());
        } catch (NumberFormatException e) {
            System.err.println(String.format("\nInvalid input %s.\n", Arrays.toString(args)));
            System.out.println(usage());
        }
    }
}
