package com.escuela.colegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionController {


    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        String errorMsg = null;
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        if (exception.getMessage() != null) {
            errorMsg = exception.getMessage();
        } else if (exception.getCause() != null) {
            errorMsg = exception.getCause().toString();
        } else if (exception != null) {
            errorMsg = exception.toString();
        }
        errorPage.addObject("errormsg", exception.getMessage());
        return errorPage;
    }

//    @ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class})
//    public ModelAndView handleException(RuntimeException ex) {
//        //Exception handling logic
//    }
}
