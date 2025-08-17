package org.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Size(min = 2, max = 20, message = "name length must be max 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "major is required")
    @Size(min = 2, max = 20, message = "major length must be max 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String major;

    @NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
