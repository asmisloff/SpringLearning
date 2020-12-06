package ru.asmisloff.spring.ht06.controllers.utils;

import ru.asmisloff.spring.ht06.exceptions.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    @ResponseBody
//    public String error(ResourceNotFoundException ex) {
//        return ex.getMessage();
//    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public String error(
            ResourceNotFoundException ex,
            Model model
    ) {
        System.out.println(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "505";
    }

}
