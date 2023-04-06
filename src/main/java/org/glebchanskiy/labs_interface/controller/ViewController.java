package org.glebchanskiy.labs_interface.controller;

import lombok.AllArgsConstructor;
import org.glebchanskiy.labs_interface.exception.MessageRecipientNotFoundException;
import org.glebchanskiy.labs_interface.model.Message;
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
    public String view(@ModelAttribute("message") Message message,
                       @ModelAttribute("answer") Message answer)
    {
        return "main";
    }

    @PostMapping()
    public String input(@ModelAttribute Message messageDto, Model model) {
        System.out.println(messageDto);
        model.addAttribute("answer", dispatchService.dispatch(messageDto));
        return "main";
    }

    @ExceptionHandler(MessageRecipientNotFoundException.class)
    public String handleMessageRecipientNotFoundException(MessageRecipientNotFoundException exception, Model model) {
        Message errorMessage = new Message(
                exception.getMessage(),
                exception.getRecipient(),
                "View"
        );
        model.addAttribute("answer", errorMessage);
        return "main";
    }
}