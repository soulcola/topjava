package ru.javawebinar.topjava.util.exception;

import java.util.List;

public class ErrorInfo {
    private String url;
    private ErrorType type;
    private String message;
    private List<String> details;

    public ErrorInfo(CharSequence url, ErrorType type, List<String> details) {
        this.url = url.toString();
        this.type = type;
        this.message = type.getPropertyName();
        this.details = details;
    }

    public ErrorInfo() {
    }

    public String getUrl() {
        return url;
    }

    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }
}