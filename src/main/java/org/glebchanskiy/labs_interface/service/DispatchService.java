package org.glebchanskiy.labs_interface.service;

import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Message;

public interface DispatchService {
    Answer dispatch(Message message);
}
