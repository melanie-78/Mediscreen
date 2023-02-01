package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.dtos.PatientDTO;
import com.openclassrooms.mediscreen.enums.Gender;
import com.openclassrooms.mediscreen.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class MediscreenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientService patientService){
		return args -> {
			PatientDTO patientDTO =
					new PatientDTO(null, "TestNone", "Test", new Date(1966, Calendar.DECEMBER,31 ), "F", "1 Brookside St", "100-222-3333");
			PatientDTO patientDTO1 =
					new PatientDTO(null, "TestBordeline", "Test", new Date(1945, Calendar.JUNE, 24), "M", "2 High St", "200-333-4444");
			PatientDTO patientDTO2 =
					new PatientDTO(null, "TestInDanger", "Test", new Date(2004, Calendar.JUNE, 18), "M", "3 Club Road", "300-444-5555");
			PatientDTO patientDTO3 =
					new PatientDTO(null,"TestEarlyOnset", "Test", new Date(2002, Calendar.JUNE, 28), "F", "4 Valley Dr", "400-555-6666");

			patientService.addPatient(patientDTO);
			patientService.addPatient(patientDTO1);
			patientService.addPatient(patientDTO2);
			patientService.addPatient(patientDTO3);
		};
	}
}
