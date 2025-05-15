package com.escuela.colegio.controller;

import com.escuela.colegio.model.ClassType;
import com.escuela.colegio.model.Person;
import com.escuela.colegio.repository.ClassesRepository;
import com.escuela.colegio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView deleteClass(Model model, @RequestParam int id){
        Optional<ClassType> classType = classesRepository.findById(id);
        for (Person person: classType.get().getPersonList()){
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

    public ModelAndView displayStudents(Model model, @RequestParam int classId) {
        ModelAndView modelAndView = new ModelAndView("students.html");
        return modelAndView;
    }
}
