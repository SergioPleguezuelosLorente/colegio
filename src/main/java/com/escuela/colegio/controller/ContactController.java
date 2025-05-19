package com.escuela.colegio.controller;

import com.escuela.colegio.model.Contact;
import com.escuela.colegio.service.ContactService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping("saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Contact from validation falied due to: " + errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping("displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model,
                                        @PathVariable(name = "pageNum") int pageNum, @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }

    @RequestMapping("closeMsg")
    public String closeMsg(@RequestParam int id, Authentication authentication) {
        contactService.updateMsgStatus(id, authentication.getName());
        return "redirect:/displayMessages";
    }

}
