package edu.mansurov.storage.exception;

public class UnknownSymbolException extends RuntimeException {
    public UnknownSymbolException() {
    }

    public UnknownSymbolException(String message) {
        super(message);
    }

    public UnknownSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSymbolException(Throwable cause) {
        super(cause);
    }

    public UnknownSymbolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
