package org.glebchanskiy.labs_interface.requester;

import org.glebchanskiy.labs_interface.model.Message;

public interface MessageRequester {
    Message getDelegatedTaskAnswer(String url, Message message);
}
