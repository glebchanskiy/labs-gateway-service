package org.glebchanskiy.labs_interface.service;
import org.glebchanskiy.labs_interface.model.Message;

public interface DispatchService {
    Message dispatch(Message message);
}
