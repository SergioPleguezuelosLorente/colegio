package com.escuela.colegio.controller;

import com.escuela.colegio.model.Address;
import com.escuela.colegio.model.Person;
import com.escuela.colegio.model.Profile;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("profileControllerBean")
public class ProfileController {

    @RequestMapping("/displayProfile")
    public ModelAndView displayMessages(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());
        Address address = person.getAddress();
        if(address != null && address.getAddressId()>0){
            profile.setAddress1(address.getAddress1());
            profile.setAddress2(address.getAddress2());
            profile.setCity(address.getCity());
            profile.setState(address.getState());
            profile.setZipCode(address.getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }
}
