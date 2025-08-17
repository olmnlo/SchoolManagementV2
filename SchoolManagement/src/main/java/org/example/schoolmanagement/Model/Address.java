package org.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String area;

    @Column(columnDefinition = "varchar(20) not null")
    private String street;

    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;

}
