package com.openclassrooms.mediscreen.services;

import com.openclassrooms.mediscreen.exceptions.PatientNotFoundException;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.dtos.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();
    PatientDTO getPatient(Integer id) throws PatientNotFoundException;
    PatientDTO updatePatient(Integer id, PatientDTO patientDTO) throws PatientNotFoundException;
    PatientDTO addPatient(PatientDTO patientDTO);

    void deletePatient(Integer id) throws PatientNotFoundException;

    List<PatientDTO> searchPatient(String keyword);
}
