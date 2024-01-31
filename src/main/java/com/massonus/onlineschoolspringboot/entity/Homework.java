package com.massonus.onlineschoolspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "homework")
@Data
public class Homework implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id", nullable = false)
    private Long id;

    @Column(name = "task")
    private String task;

    @Transient
    private transient LocalDate localDateDeadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @ToString.Exclude
    private Lecture lecture;

    @Transient
    private transient final DateTimeFormatter formatterDeadline;

    @Column(name = "deadline")
    private Date deadline;

    public Homework() {
        try {
            localDateDeadline = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() + 2);
        } catch (DateTimeException e) {
            localDateDeadline = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 1, 2);
        }
        formatterDeadline = DateTimeFormatter.ofPattern("MMM d, EEEE");
        deadline = Date.valueOf(localDateDeadline);
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
