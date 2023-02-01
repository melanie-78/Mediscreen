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

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientDTO> patientDTOs = allPatients.stream()
                .map(patient -> dtoMapper.fromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOs;
    }

    public PatientDTO getPatient(Integer id) throws PatientNotFoundException {
        Patient patientById = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("This name doesn't exist in database"));
        return dtoMapper.fromPatient(patientById);
    }

    @Override
    public PatientDTO updatePatient(Integer id, PatientDTO patientDTO) throws PatientNotFoundException {

        Patient patient = dtoMapper.fromPatientDTO(patientDTO);

        Patient savedPatient = patientRepository.save(patient);

        return dtoMapper.fromPatient(savedPatient);
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = dtoMapper.fromPatientDTO(patientDTO);

        Patient savedPatient = patientRepository.save(patient);

        return dtoMapper.fromPatient(savedPatient);
    }

    @Override
    public void deletePatient(Integer id){
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientDTO> searchPatient(String keyword) {
        List<Patient> patients = patientRepository.searchPatient(keyword);
        List<PatientDTO> patientDTOs = patients.stream()
                .map(patient -> dtoMapper.fromPatient(patient))
                .collect(Collectors.toList());
        return patientDTOs;
    }
}
