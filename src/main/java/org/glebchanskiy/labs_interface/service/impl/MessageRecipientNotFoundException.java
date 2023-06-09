package org.glebchanskiy.labs_interface.service.impl;

public class MessageRecipientNotFoundException extends RuntimeException {
    public MessageRecipientNotFoundException(String message, String recipient) {
        super(message);
        this.recipient = recipient;
    }

    private final String recipient;

    public String getRecipient() { return this.recipient; }
}
