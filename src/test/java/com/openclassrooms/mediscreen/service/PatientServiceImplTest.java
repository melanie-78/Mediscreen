package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.dtos.PatientDTO;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.exceptions.PatientNotFoundException;
import com.openclassrooms.mediscreen.mappers.PatientMapper;
import com.openclassrooms.mediscreen.repositories.PatientRepository;
import com.openclassrooms.mediscreen.services.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientMapper dtoMapper;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void getAllPatientsTest() {
        //GIVEN
        Patient patient = new Patient(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        Patient patient1 = new Patient(null, "TOUNGA", "Franck", new Date(), "M", "2 Allée Jean de Joinville", "0124589522");

        List<Patient> list = new ArrayList<>();
        list.add(patient1);
        list.add(patient);

        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO patientDTO1 = new PatientDTO(null, "TOUNGA", "Franck", new Date(), "M", "2 Allée Jean de Joinville", "0124589522");

        when(patientRepository.findAll()).thenReturn(list);

        when(dtoMapper.fromPatient(patient)).thenReturn(patientDTO);
        when(dtoMapper.fromPatient(patient1)).thenReturn(patientDTO1);


        //WHEN
        List<PatientDTO> listResult = patientService.getAllPatients();

        //THEN
        assertTrue(listResult.size() == 2);
        verify(patientRepository, Mockito.times(1)).findAll();
        verify(dtoMapper, Mockito.times(2)).fromPatient(any());
    }

    @Test
    public void addPatientTest() {
        //GIVEN
        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        Patient patient = new Patient(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        Patient savedPatient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO savedPatientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(dtoMapper.fromPatientDTO(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(savedPatient);
        when(dtoMapper.fromPatient(savedPatient)).thenReturn(savedPatientDTO);

        //WHEN
        PatientDTO actual = patientService.addPatient(patientDTO);

        //THEN
        verify(patientRepository, Mockito.times(1)).save(patient);
        assertEquals(savedPatientDTO.getId(), actual.getId());
    }

    @Test
    public void getPatientTest() throws PatientNotFoundException {
        //GIVEN
        Integer id = 1;
        Patient patient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO patientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(dtoMapper.fromPatient(patient)).thenReturn(patientDTO);

        //WHEN
       PatientDTO actual = patientService.getPatient(id);

        //THEN
        assertTrue(actual.getFirstName().equals("ADJOH"));
        verify(patientRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void updatePatientTest() {
        //GIVEN
        Integer id = 1;
        PatientDTO patientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        Patient patient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(dtoMapper.fromPatientDTO(patientDTO)).thenReturn(patient);

        Patient savedPatient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO savedPatientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(dtoMapper.fromPatientDTO(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(savedPatient);
        when(dtoMapper.fromPatient(savedPatient)).thenReturn(savedPatientDTO);

        //WHEN
        PatientDTO actual = patientService.updatePatient(id, patientDTO);

        //THEN
        verify(patientRepository, Mockito.times(1)).save(patient);
        assertEquals(savedPatientDTO.getId(), actual.getId());

    }


    @Test
    public void deletePatientTest() throws PatientNotFoundException {
        //GIVEN
        Integer id = 1;
        Patient patient = new Patient(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).delete(patient);

        //WHEN
        patientService.deletePatient(id);

        //THEN
        verify (patientRepository, Mockito.times(1)).findById(id);
        verify(patientRepository,Mockito.times(1)).delete(patient);
    }

    @Test
    public void searchPatientTest() {
        //GIVEN
        String keyword = "Melanie";

        Patient patient = new Patient(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        Patient patient1 = new Patient(null, "TOUNGA", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        List<Patient> list = new ArrayList<>();
        list.add(patient1);
        list.add(patient);

        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO patientDTO1 = new PatientDTO(null, "TOUNGA", "Melanie", new Date(), "M", "2 Allée Jean de Joinville", "0124589522");

        when(patientRepository.searchPatient(keyword)).thenReturn(list);

        when(dtoMapper.fromPatient(patient)).thenReturn(patientDTO);
        when(dtoMapper.fromPatient(patient1)).thenReturn(patientDTO1);


        //WHEN
        List<PatientDTO> listResult = patientService.searchPatient(keyword);

        //THEN
        assertTrue(listResult.size() == 2);
        verify(patientRepository, Mockito.times(1)).searchPatient(any());
    }
}
