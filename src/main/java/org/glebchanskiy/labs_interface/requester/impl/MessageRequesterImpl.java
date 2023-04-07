package org.glebchanskiy.labs_interface.requester.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.MessageRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MessageRequesterImpl implements MessageRequester {

    private final RestTemplate restTemplate;

    @Override
    public Answer getDelegatedTaskAnswer(String url, Message message) {
        try {
            Message response = restTemplate.postForEntity(
                    url,
                    message,
                    Message.class).getBody();

            if (response != null)
                return new Answer(response.getText());
            else
                return new Answer("empty");
        } catch (Exception ex) {
            throw new RequesterException("Service: " + message.getTo() + " not response.");
        }
    }
}
