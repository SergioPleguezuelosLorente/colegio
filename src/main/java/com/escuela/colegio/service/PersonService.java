package com.escuela.colegio.service;

import com.escuela.colegio.Model.Person;
import com.escuela.colegio.Model.Roles;
import com.escuela.colegio.Repository.PersonRepository;
import com.escuela.colegio.Repository.RolesRepository;
import com.escuela.colegio.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(Constants.STUDENT_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (person != null) {
            isSaved = true;
        }
        return isSaved;
    }
}
