package org.glebchanskiy.labs_interface.requester.impl;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.MessageRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MessageRequesterImpl implements MessageRequester {

    private final RestTemplate restTemplate;

    @Override
    public Message getDelegatedTaskAnswer(String url, Message message) {
        try {

            Message response = restTemplate.postForEntity(
                    url,
                    message,
                    Message.class).getBody();

            if (response != null)
                return new Message(response.getText(), response.getFrom(), response.getTo());
            else
                throw new RequesterException();
        } catch (Exception ex) {
            throw new RequesterException("Service: " + message.getTo() + " not response.");
        }
    }
}
