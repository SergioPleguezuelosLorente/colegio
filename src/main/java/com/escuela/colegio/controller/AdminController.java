package com.escuela.colegio.controller;

import com.escuela.colegio.model.ClassType;
import com.escuela.colegio.model.Person;
import com.escuela.colegio.repository.ClassesRepository;
import com.escuela.colegio.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes.html");
        List<ClassType> classesLists = classesRepository.findAll();
        modelAndView.addObject("classesLists", classesLists);
        modelAndView.addObject("classType", new ClassType());
        return modelAndView;
    }

    @RequestMapping("deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<ClassType> classType = classesRepository.findById(id);
        for (Person person : classType.get().getPersonList()) {
            person.setClassType(null);
            personRepository.save(person);
        }
        classesRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("classType") ClassType classType) {
        classesRepository.save(classType);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession httpSession,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<ClassType> classType = classesRepository.findById(classId);
        modelAndView.addObject("classType", classType.get());
        modelAndView.addObject("person", new Person());
        httpSession.setAttribute("classType", classType.get());
        if (error != null) {
            errorMessage = "Invalid Email!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudents(Model model, @ModelAttribute("person") Person person, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        ClassType classType = (ClassType) httpSession.getAttribute("classType");
        Person person1 = personRepository.readByEmail(person.getEmail());
        if (person1 == null || !(person1.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + classType.getClassId() + "&error=true");
            return modelAndView;
        }
        person1.setClassType(classType);
        personRepository.save(person1);
        classType.getPersonList().add(person1);
        classesRepository.save(classType);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + classType.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession httpSession) {
        Optional<Person> person = personRepository.findById(personId);
        person.get().setClassType(null);
        ClassType classType = (ClassType) httpSession.getAttribute("classType");
        classType.getPersonList().remove(person.get());
        ClassType classSaved = classesRepository.save(classType);
        httpSession.setAttribute("classType", classSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + classType.getClassId());
        return modelAndView;
    }
}
