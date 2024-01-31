package com.massonus.onlineschoolspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course")
@Data
public class Course implements Comparable<Course>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;

    @NotBlank (message = "name cannot be empty")
    @Column(name = "course_name")
    private String courseName;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Person> people = new ArrayList<>();

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private List<Lecture> lectures = new ArrayList<>();

    public Course() {
    }

    public void updatePeople(List<Person> people) {
        this.people.clear();
        this.people.addAll(people);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }

    @Override
    public int compareTo(Course o) {
        return this.courseName.compareTo(o.courseName);
    }
}

