package com.escuela.colegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model) {
        String errorMessage = null;
        if (!error.isEmpty()) {
            errorMessage = "Username or Password is incorrect!";
        }
        if (!logout.isEmpty()){
            errorMessage = "You have been sucessfully logged out!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }
}
