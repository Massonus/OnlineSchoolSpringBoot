package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.entity.Person;
import com.massonus.onlineschoolspringboot.entity.Role;
import com.massonus.onlineschoolspringboot.service.CourseService;
import com.massonus.onlineschoolspringboot.service.LectureService;
import com.massonus.onlineschoolspringboot.service.Menu;
import com.massonus.onlineschoolspringboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    private final Menu menu;
    private final PersonService personService;
    private final LectureService lectureService;
    private final CourseService courseService;

    @Autowired
    public PersonController(Menu menu, PersonService personService, LectureService lectureService, CourseService courseService) {
        this.menu = menu;
        this.personService = personService;
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    @GetMapping("/person/{id}")
    public String getStudent(Model model, @PathVariable long id) {
        final Person person = personService.getPersonById(id).orElse(null);
        model.addAttribute("person", person);
        model.addAttribute("menu", menu.getMenuItems());
        return "info/person_info";
    }

    @GetMapping("/all-people")
    public String home(Model model) {
        model.addAttribute("menu", menu.getMenuItems());
        model.addAttribute("people", personService.getPeopleList());
        model.addAttribute("lectures", lectureService.getLectureList());
        model.addAttribute("courses", courseService.getCourseList());
        return "menu/person_menu";
    }

    @PostMapping("/addPerson")
    public String addMaterial(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String phone,
                              @RequestParam String email,
                              @RequestParam String role,
                              @RequestParam List<Integer> lectureIdList,
                              @RequestParam List<Integer> courseIdList) {

        final Person newPerson = personService.createElementByUserForm(firstName, lastName, phone, email, Role.valueOf(role), lectureIdList, courseIdList);
        /*personService.savePerson(newPerson);*/
        return "redirect:/all-people";
    }
}
