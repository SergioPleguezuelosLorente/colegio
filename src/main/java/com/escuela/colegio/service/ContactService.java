package com.escuela.colegio.service;

import com.escuela.colegio.Model.Contact;
import com.escuela.colegio.Model.Holiday;
import com.escuela.colegio.Repository.ContactRepository;
import com.escuela.colegio.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.List;

@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN);
        contact.setCreatedBy(Constants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        contactRepository.saveContactMsg(contact);
        log.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(Constants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int id, String updateBy) {
        int result = contactRepository.updateMsgStatus(id, Constants.CLOSE, updateBy);
        if (result > 0 ) {
            return true;
        }
        return false;
    }
}
