package org.glebchanskiy.labs_interface.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private String chosenService;
    private String textInput;
}
