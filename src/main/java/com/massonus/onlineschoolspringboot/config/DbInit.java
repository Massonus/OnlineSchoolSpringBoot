package com.massonus.onlineschoolspringboot.config;

import com.massonus.onlineschoolspringboot.entity.Role;
import com.massonus.onlineschoolspringboot.entity.User;
import com.massonus.onlineschoolspringboot.service.CourseService;
import com.massonus.onlineschoolspringboot.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collections;

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
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setRoles(Collections.singleton(Role.ADMIN));
        userService.addUser(user);
    }
}
