package org.glebchanskiy.labs_interface.requester.impl;

public class RequesterException extends RuntimeException {
    public RequesterException(String message) {
        super(message);
    }

    public RequesterException() {
    }
}
