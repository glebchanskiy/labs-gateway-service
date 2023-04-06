package org.glebchanskiy.labs_interface.service.impl;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.exception.MessageRecipientNotFoundException;
import org.glebchanskiy.labs_interface.exception.ServiceNotResponseException;
import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class DispatchServiceImpl implements DispatchService {
    private final RestTemplate restTemplate;

    @Override
    public Answer dispatch(Message message) {
        System.out.println(message);
        switch (message.getTo()) {
            case "pdnf-validator" -> {
                return new Answer(toPcnfValidator(message).getText());
            }
            case "test1" -> {
                return new Answer("test1 answer");
            }
            case "test2" -> {
                return new Answer("test2 answer");
            }
        }
        System.out.println("EXCEPTION");
        throw new MessageRecipientNotFoundException("Message from [" + message.getFrom() + "]. Recipient not found.", message.getTo());
    }

    private Message toPcnfValidator(Message message) {
        try {
            return restTemplate.postForEntity(
                    "http://localhost:8050/validate",
                    message,
                    Message.class).getBody();
        } catch (RestClientException e) {
            throw new ServiceNotResponseException("Service: " + message.getTo() + " not response.");
        }
    }
}
