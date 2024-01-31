package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.service.CourseService;
import com.massonus.onlineschoolspringboot.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@Controller
public class MainController {
    private final Menu menu;

    @Autowired
    public MainController(CourseService courseService, Menu menu) {
        this.menu = menu;

        for (int i = 0; i < 4; i++) {
            courseService.createElementAuto();
        }
    }

    @GetMapping(value = "/static/css/{cssFile}")
    public @ResponseBody byte[] getFile(@PathVariable("cssFile") String cssFile) {

        InputStream in = getClass()
                .getResourceAsStream("/templates/" + cssFile);
        try {
            return in.readAllBytes();

        } catch (Exception e) {
            String error = "ERROR: css file (/templates/" + cssFile + ") not found";
            return error.getBytes();
        }
    }

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("message", "Main menu");
        model.addAttribute("menu", menu.getMenuItems());
        return "index";
    }
}
