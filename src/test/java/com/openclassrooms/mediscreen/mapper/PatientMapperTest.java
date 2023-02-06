package com.openclassrooms.mediscreen.mapper;

import com.openclassrooms.mediscreen.dtos.PatientDTO;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.mappers.PatientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PatientMapperTest {
    @InjectMocks
    private PatientMapper patientMapper;

    @Test
    public void fromPatientTest(){
        //GIVEN
        Patient patient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO expected = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        //WHEN
        PatientDTO actual = patientMapper.fromPatient(patient);

        //THEN
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getNumber(), actual.getNumber());

    }

    @Test
    public void fromPatientDTOTest(){
        //GIVEN
        PatientDTO patientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        Patient expected = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        //WHEN
        Patient actual = patientMapper.fromPatientDTO(patientDTO);

        //THEN
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getNumber(), actual.getNumber());
    }
}
