package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.Objects;

@Controller
public class MainController {
    private final Menu menu;

    @Autowired
    public MainController(Menu menu) {
        this.menu = menu;
    }

    @GetMapping(value = "/static/css/{cssFile}")
    public @ResponseBody byte[] getFile(@PathVariable("cssFile") String cssFile) {


        try (InputStream in = getClass()
                .getResourceAsStream("/templates/" + cssFile)) {
            return Objects.requireNonNull(in).readAllBytes();

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

    @GetMapping("/test")
    public String test () {
        return "test";
    }
}
