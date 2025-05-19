package com.escuela.colegio.service;

import com.escuela.colegio.config.Constants;
import com.escuela.colegio.model.Contact;
import com.escuela.colegio.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        log.info(contact.toString());
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sordDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sordDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> contactMsgs = contactRepository.findByStatus(Constants.OPEN, pageable);
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
        return isUpdated;
    }
}
