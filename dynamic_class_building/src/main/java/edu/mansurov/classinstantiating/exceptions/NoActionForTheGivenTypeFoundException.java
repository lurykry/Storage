package edu.mansurov.classinstantiating.exceptions;

public class NoActionForTheGivenTypeFoundException extends RuntimeException {

    public NoActionForTheGivenTypeFoundException() {
    }

    public NoActionForTheGivenTypeFoundException(String message) {
        super(message);
    }

    public NoActionForTheGivenTypeFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoActionForTheGivenTypeFoundException(Throwable cause) {
        super(cause);
    }

    public NoActionForTheGivenTypeFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
