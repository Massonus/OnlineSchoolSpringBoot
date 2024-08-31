package com.massonus.onlineschoolspringboot.config;

import com.massonus.onlineschoolspringboot.entity.Lecture;
import com.massonus.onlineschoolspringboot.entity.Person;
import com.massonus.onlineschoolspringboot.entity.Role;
import com.massonus.onlineschoolspringboot.entity.User;
import com.massonus.onlineschoolspringboot.service.CourseService;
import com.massonus.onlineschoolspringboot.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Random;

@Component
public class DbInit {
    private final CourseService courseService;
    private final UserService userService;

    public DbInit(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 4; i++) {
            courseService.createElementAuto();
        }

        final User user = new User();
        user.setEmail("user@gmail.com");
        user.setName("cat");
        user.setPassword("cat");

        userService.addUser(user, false);

        final User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setName("admin");
        admin.setPassword("admin");

        userService.addUser(admin, true);
    }
}
