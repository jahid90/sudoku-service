package org.jahiduls.sudokuservice.exceptions;

import org.springframework.util.StringUtils;

/**
 * Represents a server-side exception that can not be recovered from.
 *
 * Is an unchecked exception.
 */
public class ApplicationException extends RuntimeException {

    private ApplicationException(String message) {
        super(message);
    }

    private ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ApplicationExceptionBuilder newException() {
        return new ApplicationExceptionBuilder();
    }

    public static class ApplicationExceptionBuilder {

        private String message;
        private Throwable throwable;

        public ApplicationExceptionBuilder withMessage(String message) {
            this.message = message;

            return this;
        }

        public ApplicationExceptionBuilder withThrowable(Throwable throwable) {
            this.throwable = throwable;

            return this;
        }

        public ApplicationException build() {

            if (StringUtils.isEmpty(message)) {
                throw new RuntimeException("Missing exception message");
            }

            if (null == throwable) {
                return new ApplicationException(message);
            }

            return new ApplicationException(message, throwable);
        }
    }
}
