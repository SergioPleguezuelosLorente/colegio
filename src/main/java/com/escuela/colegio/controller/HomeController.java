package com.escuela.colegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"", "/", "/home"})
    public String homepage(Model model) {
        model.addAttribute("usuario", "Luisma");
        return "home.html";
    }

}
