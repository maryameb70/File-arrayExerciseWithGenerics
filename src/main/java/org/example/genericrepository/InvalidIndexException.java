package org.example.genericrepository;

public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
    }

    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIndexException(Throwable cause) {
        super(cause);
    }

    public InvalidIndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
