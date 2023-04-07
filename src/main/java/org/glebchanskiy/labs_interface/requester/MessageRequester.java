package org.glebchanskiy.labs_interface.requester;

import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Message;

public interface MessageRequester {
    Answer getDelegatedTaskAnswer(String url, Message message);
}
