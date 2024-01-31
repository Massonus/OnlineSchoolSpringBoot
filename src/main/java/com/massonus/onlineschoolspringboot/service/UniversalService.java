package com.massonus.onlineschoolspringboot.service;

import java.util.List;

public interface UniversalService<T> {

    default boolean printAll(List<T> eList) {
        if (eList == null) {
            System.out.println("Please create an Array");
            return false;
        }
        int i = 0;
        for (T element : eList) {
            System.out.println("\n index: " + i + " " + element + "\n");
            i++;
        }
        return true;
    }
}
