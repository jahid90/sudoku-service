package org.jahiduls.sudokuservice.exceptions;

import org.springframework.util.StringUtils;

/**
 * Represents an exception of an unsolvable puzzle.
 *
 * Is a checked exception.
 */
public class UnsolvablePuzzleException extends Exception {

    private UnsolvablePuzzleException(String s) {
        super(s);
    }

    private UnsolvablePuzzleException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InvalidFormatExceptionBuilder newException() {
        return new InvalidFormatExceptionBuilder();
    }

    public static class InvalidFormatExceptionBuilder {

        private String message;
        private Throwable throwable;

        public InvalidFormatExceptionBuilder withMessage(String message) {

            this.message = message;

            return this;
        }

        public InvalidFormatExceptionBuilder withThrowable(Throwable throwable) {

            this.throwable = throwable;

            return this;
        }

        public UnsolvablePuzzleException build() {

            if (StringUtils.isEmpty(message)) {
                throw new RuntimeException("Missing exception message");
            }

            if (null == throwable) {
                return new UnsolvablePuzzleException(message);
            }

            return new UnsolvablePuzzleException(message, throwable);
        }

    }
}
