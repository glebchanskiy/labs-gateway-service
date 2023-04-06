package org.glebchanskiy.labs_interface.exception;

public class ServiceNotResponseException extends RuntimeException {
    public ServiceNotResponseException(String message) {
        super(message);
    }
}
