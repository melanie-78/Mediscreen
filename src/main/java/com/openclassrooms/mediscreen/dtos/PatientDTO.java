package com.openclassrooms.mediscreen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Integer id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "Birthdate is mandatory")
    private Date birthDate;
    @NotBlank(message = "type is mandatory")
    private String type;
    private String address;
    private String number;
}
