package org.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Size(min = 2, max = 20, message = "name length must be max 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "email is required")
    @Email(message = "not valid email")
    @Size(max = 30, message = "email max length 30")
    @Column(columnDefinition = "varchar(30) unique not null")
    private String email;

    @NotNull(message = "salary is required")
    @Positive(message = "salary must be positive")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;

}
