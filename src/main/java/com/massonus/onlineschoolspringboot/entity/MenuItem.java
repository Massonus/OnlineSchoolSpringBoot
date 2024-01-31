package com.massonus.onlineschoolspringboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuItem {
    private String link;
    private String label;

    public MenuItem() {
    }

    public MenuItem(String link, String label) {
        this.link = link;
        this.label = label;
    }
}
