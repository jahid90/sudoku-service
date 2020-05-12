package org.jahiduls.sudokuservice.exceptions;

import org.springframework.util.StringUtils;

/**
 * Represents a client-side exception, due to invalid input.
 *
 * Is a checked exception.
 */
public class InvalidFormatException extends Exception {

    private InvalidFormatException(String s) {
        super(s);
    }

    private InvalidFormatException(String message, Throwable cause) {
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

        public InvalidFormatException build() {

            if (StringUtils.isEmpty(message)) {
                throw new RuntimeException("Missing exception message");
            }

            if (null == throwable) {
                return new InvalidFormatException(message);
            }

            return new InvalidFormatException(message, throwable);
        }

    }
}
