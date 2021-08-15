package edu.mansurov.storage.domain.validator;

public class ValidatorResponse {
    private final boolean isQueryValid;
    private String reason;

    private ValidatorResponse(boolean isQueryValid) {
        this.isQueryValid = isQueryValid;
    }

    private ValidatorResponse(String reason) {
        this.isQueryValid = false;
        this.reason = reason;
    }

    public static ValidatorResponse validationFailed(String reason) {
        return new ValidatorResponse(reason);
    }

    public static ValidatorResponse validationSucceeded() {
        return new ValidatorResponse(true);
    }

    public boolean isQueryValid() {
        return isQueryValid;
    }

    public String getReason() {
        return reason;
    }
}
