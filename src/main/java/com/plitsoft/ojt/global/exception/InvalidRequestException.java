package com.plitsoft.ojt.global.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() { super("올바르지 않은 요청입니다"); }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String key, String value) { super( String.format("올바르지 않은 %s 요청입니다: %s", key, value) ); }
}
