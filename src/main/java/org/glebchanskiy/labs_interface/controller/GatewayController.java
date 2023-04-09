package org.glebchanskiy.labs_interface.controller;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.impl.RequesterException;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.glebchanskiy.labs_interface.service.impl.MessageRecipientNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/")
public class GatewayController {

    private final DispatchService dispatchService;

    @CrossOrigin()
    @PostMapping("/dispatch")
    public Message dispatch(@RequestBody Message message) {
        System.out.println(message);
        return dispatchService.dispatch(message);
    }

    @CrossOrigin
    @ExceptionHandler({RequesterException.class, MessageRecipientNotFoundException.class})
    public Message handleServiceNotResponseException(Exception exception) {
        return new Message(exception.getMessage(), "dispatch", "view");
    }

}
