package com.plitsoft.ojt.global.exception;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException() {
        super( "중복된 값이 입력되었습니다." );
    }

    public DuplicateValueException(String message) {
        super( message );
    }
}
