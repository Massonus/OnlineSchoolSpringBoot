package com.massonus.onlineschoolspringboot.service;

import com.massonus.onlineschoolspringboot.entity.MenuItem;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Menu {

    private final List<MenuItem> menuItems = new ArrayList<>();
    {
        menuItems.add(new MenuItem("/", "Main"));
        menuItems.add(new MenuItem("/all-courses", "All courses"));
        menuItems.add(new MenuItem("/all-materials", "All materials"));
        menuItems.add(new MenuItem("/all-homework", "All homework"));
        menuItems.add(new MenuItem("/all-lectures", "All Lectures"));
        menuItems.add(new MenuItem("/all-people", "All people"));
    }

}
