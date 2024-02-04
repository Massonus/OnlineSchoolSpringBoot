package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.entity.Person;
import com.massonus.onlineschoolspringboot.entity.Position;
import com.massonus.onlineschoolspringboot.service.CourseService;
import com.massonus.onlineschoolspringboot.service.LectureService;
import com.massonus.onlineschoolspringboot.service.Menu;
import com.massonus.onlineschoolspringboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

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
        model.addAttribute("lectures", lectureService.getLectureList());
        model.addAttribute("courses", courseService.getCourseList());
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

        personService.createElementByUserForm(firstName, lastName, phone, email, Position.valueOf(role), lectureIdList, courseIdList);
        return "redirect:/all-people";
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        final Person person = personService.getPersonById(id).orElse(null);
        personService.deletePerson(Objects.requireNonNull(person).getId());
        return "redirect:/all-people";
    }

    @PostMapping("/person/edit/{id}")
    public String postEditPerson(@PathVariable Long id,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String phone,
                                 @RequestParam String email,
                                 @RequestParam String role,
                                 @RequestParam List<Integer> lectureIdList,
                                 @RequestParam List<Integer> courseIdList) {

        final Person person = personService.getPersonById(id).get();
        final Person editedPerson = personService.refactorElementByUserForm(person, firstName, lastName, phone, email, Position.valueOf(role), lectureIdList, courseIdList);
        personService.savePerson(editedPerson);

        return "redirect:/all-people";
    }
}
