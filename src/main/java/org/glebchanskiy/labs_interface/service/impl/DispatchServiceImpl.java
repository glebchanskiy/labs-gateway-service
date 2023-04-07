package org.glebchanskiy.labs_interface.service.impl;

import lombok.RequiredArgsConstructor;
import org.glebchanskiy.labs_interface.exception.MessageRecipientNotFoundException;
import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.MessageRequester;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {
    private final MessageRequester messageRequester;
    private final Map<Message, Answer> cache;

    @Value("${service.lois-service-lab1.host}")
    private String loisLab1Url;
    @Value("${service.aois-service-lab3.host}")
    private String aoisLab3Url;


    @Override
    public Answer dispatch(Message message) {
        Answer answerInCache = cache.get(message);
        if (answerInCache != null) {
            System.out.println("cached: " + answerInCache);
            return answerInCache;
        }
        else {
            switch (message.getTo()) {
                case "lois-service-lab1" -> {
                    Answer answer = messageRequester.getDelegatedTaskAnswer(loisLab1Url, message);
                    cache.put(message, answer);
                    return answer;
                }
                case "aois-service-lab3" -> {
                    Answer answer = messageRequester.getDelegatedTaskAnswer(aoisLab3Url, message);
                    cache.put(message, answer);
                    return answer;
                }
            }
            throw new MessageRecipientNotFoundException("Message from [" + message.getFrom() + "]. Recipient not found.", message.getTo());
        }
    }
}
