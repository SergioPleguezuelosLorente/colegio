package com.escuela.colegio.controller;

import com.escuela.colegio.model.ClassType;
import com.escuela.colegio.model.Courses;
import com.escuela.colegio.model.Person;
import com.escuela.colegio.repository.ClassesRepository;
import com.escuela.colegio.repository.CoursesRepository;
import com.escuela.colegio.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    CoursesRepository coursesRepository;

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
    public ModelAndView addStudents(Model model, @ModelAttribute("person") Person email, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        ClassType classType = (ClassType) httpSession.getAttribute("classType");
        Person person = personRepository.readByEmail(email.getEmail());
        if (person == null || !(person.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + classType.getClassId() + "&error=true");
            return modelAndView;
        }
        person.setClassType(classType);
        personRepository.save(person);
        classType.getPersonList().add(person);
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

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        List<Courses> courses = coursesRepository.findAll(Sort.by("name").descending());
        modelAndView.addObject("courses", courses);
        model.addAttribute("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses courses) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(courses);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session, @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        String errorMessage = null;
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("courses", courses.get());
        if (error != null) {
            errorMessage = "Invalid email!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person email, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) httpSession.getAttribute("courses");
        Person person = personRepository.readByEmail(email.getEmail());
        if (person == null || person.getPersonId() < 0) {
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId() + "&error=true");
            return modelAndView;
        }
        person.getCourses().add(courses);
        courses.getPersonList().add(person);
        personRepository.save(person);
        httpSession.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId());
        return modelAndView;
    }

    @GetMapping("deleteStudentFromCourse")
    public ModelAndView removeStudentFromCourse(Model mode, @RequestParam int personId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) httpSession.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersonList().remove(person);
        personRepository.save(person.get());
        httpSession.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId());
        return modelAndView;
    }
}
