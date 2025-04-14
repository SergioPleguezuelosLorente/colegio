package com.escuela.colegio.service;

import com.escuela.colegio.Model.Contact;
import com.escuela.colegio.controller.ContactController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        //TODO añadir esto a base de datos
        log.info(contact.toString());
        return isSaved;
    }
}
