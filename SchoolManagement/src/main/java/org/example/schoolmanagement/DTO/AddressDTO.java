package org.example.schoolmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {

    @NotEmpty(message = "area is required")
    @Size(min = 2, max = 20, message = "area length must be max 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String area;

    @NotEmpty(message = "street is required")
    @Size(min = 2, max = 20, message = "street length must be max 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String street;

    @NotNull(message = "buildingNumber is required")
    @Positive(message = "building number must be positive")
    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;

    @NotNull(message = "teacherId is required")
    @Positive(message = "teacherId must be positive")
    private Integer teacherId;
}
