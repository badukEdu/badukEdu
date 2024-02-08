package org.choongang.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;
/**
 * 메세지 번들 예외 처리
 */
public class CommonException extends RuntimeException {

    protected static ResourceBundle bundleValidation;
    protected static ResourceBundle bundleError;
    private HttpStatus status;

    static {
        bundleValidation = ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");
    }

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CommonException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
