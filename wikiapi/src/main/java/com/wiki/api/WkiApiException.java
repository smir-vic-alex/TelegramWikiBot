package com.wiki.api;

public class WkiApiException extends RuntimeException {
    public WkiApiException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
