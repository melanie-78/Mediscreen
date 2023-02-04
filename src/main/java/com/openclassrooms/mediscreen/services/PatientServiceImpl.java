package com.openclassrooms.mediscreen.services;

import com.openclassrooms.mediscreen.exceptions.PatientNotFoundException;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.mappers.PatientMapper;
import com.openclassrooms.mediscreen.repositories.PatientRepository;
import com.openclassrooms.mediscreen.dtos.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private PatientMapper dtoMapper;

    /**
     *
     * @return a list of data about patients saved in database
     */
    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientDTO> patientDTOs = allPatients.stream()
                .map(patient -> dtoMapper.fromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOs;
    }

    /**
     *
     * @param id is the identifier of a patient
     * @return data about patient identified by id
     * @throws PatientNotFoundException if patient doesn't exist in database
     */
    @Override
    public PatientDTO getPatient(Integer id) throws PatientNotFoundException {
        Patient patientById = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("This name doesn't exist in database"));
        return dtoMapper.fromPatient(patientById);
    }

    /**
     * this permits to update patient's data
     * @param id is the identifier of a patient
     * @param patientDTO data about patient identified by id
     * @return patientDTO updated
     */

    @Override
    public PatientDTO updatePatient(Integer id, PatientDTO patientDTO){

        Patient patient = dtoMapper.fromPatientDTO(patientDTO);

        Patient savedPatient = patientRepository.save(patient);

        return dtoMapper.fromPatient(savedPatient);
    }

    /**
     * This service permits to save a patient in database
     * @param patientDTO data about patient
     * @return patientDTO saved
     */
    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = dtoMapper.fromPatientDTO(patientDTO);

        Patient savedPatient = patientRepository.save(patient);

        return dtoMapper.fromPatient(savedPatient);
    }

    /**
     * This permits to delete a patient from database
     * @param id is the identifier of a patient
     * @throws PatientNotFoundException
     */
    @Override
    public void deletePatient(Integer id) throws PatientNotFoundException {
        Patient patientById = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("This name doesn't exist in database"));
                patientRepository.delete(patientById);
    }

    /**
     *
     * @param keyword is a word enter to research a patient
     * @return a list of PatientDTO
     */

    @Override
    public List<PatientDTO> searchPatient(String keyword) {
        List<Patient> patients = patientRepository.searchPatient(keyword);
        List<PatientDTO> patientDTOs = patients.stream()
                .map(patient -> dtoMapper.fromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOs;
    }
}
