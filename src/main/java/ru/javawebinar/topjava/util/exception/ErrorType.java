package ru.javawebinar.topjava.util.exception;

public enum ErrorType {
    APP_ERROR("common.appError"),
    DATA_NOT_FOUND("common.dataNotFound"),
    DATA_ERROR("common.dataError"),
    VALIDATION_ERROR("common.validationError");

    private final String propertyName;

    ErrorType(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return this.propertyName;
    }
}
