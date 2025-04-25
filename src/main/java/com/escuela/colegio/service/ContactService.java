package com.escuela.colegio.service;

import com.escuela.colegio.Model.Contact;
import com.escuela.colegio.Repository.ContactRepository;
import com.escuela.colegio.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Contact savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        //DEPRECATED
        //JDBC example:
//        int result = contactRepository.saveContactMsg(contact);
//        if (result > 0) {
//            isSaved = true;
//        }
        log.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findByStatus(Constants.OPEN);
        //DEPRECATED
        //JDBC example:
//        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(Constants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int id, String updateBy) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent((contact1 -> {
            contact1.setStatus(Constants.CLOSE);
            contact1.setUpdatedBy(updateBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        }));
        Contact updatedContact = contactRepository.save(contact.get());
        if (null != updatedContact && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        //DEPRECATED
        //JDBC example:
//        int result = contactRepository.updateMsgStatus(id, Constants.CLOSE, updateBy);
//        if (result > 0) {
//            isUpdated = true;
//        }
        return isUpdated;
    }
}
