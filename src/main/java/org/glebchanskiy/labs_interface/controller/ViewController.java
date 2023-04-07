package org.glebchanskiy.labs_interface.controller;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.exception.MessageRecipientNotFoundException;
import org.glebchanskiy.labs_interface.exception.ServiceNotResponseException;
import org.glebchanskiy.labs_interface.model.Answer;
import org.glebchanskiy.labs_interface.model.Form;
import org.glebchanskiy.labs_interface.model.Message;
import org.glebchanskiy.labs_interface.requester.impl.RequesterException;
import org.glebchanskiy.labs_interface.service.DispatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ViewController {

    private final DispatchService dispatchService;

    @GetMapping()
    public String view(@ModelAttribute("form") Form form) {
        return "main";
    }

    @PostMapping()
    public String input(@ModelAttribute("form") Form form,
                        Model model) {
        Message message = new Message(form.getTextInput(), "view", form.getChosenService());
        Answer answer = dispatchService.dispatch(message);
        model.addAttribute("answer", answer);
        return "main";
    }

    @ExceptionHandler(MessageRecipientNotFoundException.class)
    public String handleMessageRecipientNotFoundException(MessageRecipientNotFoundException exception,
                                                          Model model
    ) {
        model.addAttribute("form", new Form());
        model.addAttribute("answer", new Answer(exception.getMessage()));
        return "main";
    }

    @ExceptionHandler(RequesterException.class)
    public String handleServiceNotResponseException(RequesterException exception,
                                                    Model model
    ) {
        model.addAttribute("form", new Form());
        model.addAttribute("answer", new Answer(exception.getMessage()));
        return "main";
    }
}