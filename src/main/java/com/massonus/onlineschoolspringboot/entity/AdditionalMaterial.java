package com.massonus.onlineschoolspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "additional_material")
@Data
public class AdditionalMaterial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long id;

    @NotBlank (message = "Task cannot be empty")
    private String task;

    @Column(columnDefinition = "text", name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @ToString.Exclude
    private Lecture lecture;

    public AdditionalMaterial() {
    }

    @Override
    public String toString() {
        return "AdditionalMaterial{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", resourceType=" + resourceType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalMaterial that = (AdditionalMaterial) o;
        return Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }
}
