package org.glebchanskiy.labs_interface.service.impl;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.exception.MessageRecipientNotFoundException;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class DispatchServiceImpl implements DispatchService {
    private final RestTemplate restTemplate;

    @Override
    public Message dispatch(Message message) {
        System.out.println(message);
        switch (message.getTo()) {
            case "pcnf-validator" -> { return toPcnfValidator(message); }
            case "test1" -> new Message("bla bla", "test1", "view");
            case "test2" -> new Message("bla", "test2", "view");
        }
        throw new MessageRecipientNotFoundException("Message from [" + message.getFrom() + "]. Recipient not found.", message.getTo());
    }

    private Message toPcnfValidator(Message message) {
        Message response = restTemplate.postForEntity("http://localhost:8050/validate", message, Message.class).getBody();
        System.out.println(response);
        return response;
    }
}
