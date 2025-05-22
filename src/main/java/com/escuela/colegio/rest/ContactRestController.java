package com.escuela.colegio.rest;

import com.escuela.colegio.config.Constants;
import com.escuela.colegio.model.Contact;
import com.escuela.colegio.model.Response;
import com.escuela.colegio.repository.ContactRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/contact")
public class ContactRestController {

    private static final Logger log = LoggerFactory.getLogger(ContactRestController.class);
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
//    @ResponseBody //Not necessary anymore because we added @RestController to the class
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
//    @ResponseBody
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {
        if (contact != null && contact.getStatus() != null) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
                                            @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusCode("Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved", "true")
                .body(response);

    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusCode("Message succesfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
