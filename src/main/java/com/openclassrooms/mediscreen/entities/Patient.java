package com.openclassrooms.mediscreen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "BirthDate is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    //@Enumerated(EnumType.STRING)
    @NotBlank(message = "Gender is mandatory")
    private String type;
    private String address;
    private String number;
}
