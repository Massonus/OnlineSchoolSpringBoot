package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.entity.Homework;
import com.massonus.onlineschoolspringboot.service.HomeworkService;
import com.massonus.onlineschoolspringboot.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeworkController {

    private final Menu menu;
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(Menu menu, HomeworkService homeworkService) {
        this.menu = menu;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/homework/{id}")
    public String getStudent(Model model, @PathVariable long id) {
        final Homework homework = homeworkService.getHomeworkById(id).orElse(null);
        model.addAttribute("homework", homework);
        model.addAttribute("menu", menu.getMenuItems());
        return "info/homework_info";
    }

    @GetMapping("/all-homework")
    public String home(Model model) {
        model.addAttribute("menu", menu.getMenuItems());
        model.addAttribute("homeworks", homeworkService.getHomeworkList());
        return "menu/homework_menu";
    }

    @PostMapping("/addHomework")
    public String addMaterial(@RequestParam String task,
                              @RequestParam Long lectureId) {
        final Homework newHomework = homeworkService.createElementByUserForm(task, lectureId);
        homeworkService.saveHomework(newHomework);
        return "redirect:/all-homework";
    }
}
