package com.massonus.onlineschoolspringboot.config;

import com.massonus.onlineschoolspringboot.service.CourseService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final CourseService courseService;

    public DbInit(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 4; i++) {
            courseService.createElementAuto();
        }

    }
}
