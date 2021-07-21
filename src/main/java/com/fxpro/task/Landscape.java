package com.fxpro.task;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.fxpro.task.Constants.MAX_HEIGHT;
import static com.fxpro.task.Constants.MAX_POSITION;
import static com.fxpro.task.Constants.MIN_HEIGHT;

public class Landscape {

    private final int[] positions;
    private final String validationError;

    public Landscape(Builder builder) {
        this.positions = builder.positions;
        this.validationError = builder.validationError;
    }

    public int[] getPositions() {
        return this.positions;
    }

    /**
     * @return validation error message if present.
     */
    public Optional<String> getValidationError() {
        return Objects.isNull(this.validationError) ? Optional.empty() : Optional.of(this.validationError);
    }

    public static class Builder {
        private final int[] positions;
        private String validationError = null;

        public Builder(int[] positions) {
            this.positions = positions;
        }

        public Landscape build() {
            validate();
            return new Landscape(this);
        }

        private void validate() {
            if (positions.length > MAX_POSITION) {
                validationError = String.format("\nInvalid input, positions cannot be more than %d.\n", MAX_POSITION);
            } else if (positions.length == 0) {
                validationError = "\nInvalid input, positions cannot be 0.\n";
            } else if (Arrays.stream(positions).anyMatch(value -> value > MAX_HEIGHT || value < MIN_HEIGHT)) {
                validationError = String.format("\nInvalid input, position height cannot be negative or greater than %d.\n", MAX_HEIGHT);
            }
        }
    }
}
