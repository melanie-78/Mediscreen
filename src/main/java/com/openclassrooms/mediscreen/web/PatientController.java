package com.openclassrooms.mediscreen.web;

import com.openclassrooms.mediscreen.exceptions.PatientNotFoundException;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.services.PatientService;
import com.openclassrooms.mediscreen.dtos.PatientDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PatientController {
    private PatientService patientService;


    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/patients/search")
    public List<PatientDTO> searchPatient(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return patientService.searchPatient("%"+keyword+"%");
    }

    @GetMapping("/patient/{id}")
    public PatientDTO getPatient(@PathVariable Integer id) throws PatientNotFoundException {
        return patientService.getPatient(id);
    }

    @PostMapping("/patient/add")
    public ResponseEntity<PatientDTO> addPatient(@Valid @RequestBody PatientDTO patientDTO){
        PatientDTO patientAdded = patientService.addPatient(patientDTO);
        return ResponseEntity.ok().body(patientAdded);
    }

    @PutMapping("/patient/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Integer id, @Valid @RequestBody PatientDTO patientDTO) throws PatientNotFoundException {

        patientDTO.setId(id);

        PatientDTO patientUpdate = patientService.updatePatient(id, patientDTO);

        return ResponseEntity.ok().body(patientUpdate);
    }

    @DeleteMapping("/patient/delete/{id}")
    public void deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
    }
}
