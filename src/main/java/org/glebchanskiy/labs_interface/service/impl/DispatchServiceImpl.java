package org.glebchanskiy.labs_interface.service.impl;

import lombok.RequiredArgsConstructor;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.MessageRequester;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {
    private final MessageRequester messageRequester;

    @Value("${service.lois-service-lab1.host}")
    private String loisLab1Url;
    @Value("${service.aois-service-lab3.host}")
    private String aoisLab3Url;


    @Override
    @Cacheable(value = "message", key ="#message")
    public Message dispatch(Message message) {
        System.out.println(message);
        switch (message.getTo()) {
            case "lois-service-lab1" -> {
                return messageRequester.getDelegatedTaskAnswer(loisLab1Url, message);
            }
            case "aois-service-lab3" -> {
                return messageRequester.getDelegatedTaskAnswer(aoisLab3Url, message);
            }
        }
        throw new MessageRecipientNotFoundException("Message from [" + message.getFrom() + "]. Recipient not found.", message.getTo());

    }
}
