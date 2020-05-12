package org.jahiduls.sudokuservice.exceptions;

import org.springframework.util.StringUtils;

/**
 * Represents an exception of an unsolvable puzzle.
 *
 * Is a checked exception.
 */
public class InvalidPuzzleException extends Exception {

    private InvalidPuzzleException(String s) {
        super(s);
    }

    private InvalidPuzzleException(String message, Throwable cause) {
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

        public InvalidPuzzleException build() {

            if (StringUtils.isEmpty(message)) {
                throw new RuntimeException("Missing exception message");
            }

            if (null == throwable) {
                return new InvalidPuzzleException(message);
            }

            return new InvalidPuzzleException(message, throwable);
        }

    }
}
