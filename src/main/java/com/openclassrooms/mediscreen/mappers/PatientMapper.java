package com.openclassrooms.mediscreen.mappers;

import com.openclassrooms.mediscreen.dtos.PatientDTO;
import com.openclassrooms.mediscreen.entities.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class PatientMapper {
    /**
     * map patient to patientDTO
     * @param patient person to whom data have been saved in database
     * @return a patientDTO
     */
    public PatientDTO fromPatient(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    /**
     * map patientDTO to patient
     * @param patientDTO data about patient
     * @return a patient
     */
    public Patient fromPatientDTO(PatientDTO patientDTO){
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);
        return patient;
    }
}
