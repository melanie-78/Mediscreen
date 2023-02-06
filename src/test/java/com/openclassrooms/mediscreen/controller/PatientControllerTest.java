package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.dtos.PatientDTO;
import com.openclassrooms.mediscreen.entities.Patient;
import com.openclassrooms.mediscreen.exceptions.PatientNotFoundException;
import com.openclassrooms.mediscreen.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void testGetAllPatients() throws Exception {
        List<PatientDTO> list = new ArrayList<>();

        when(patientService.getAllPatients()).thenReturn(list);

        this.mockMvc
                .perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPatient() throws Exception {
        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO savedPatientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(patientService.addPatient(patientDTO)).thenReturn(savedPatientDTO);

        this.mockMvc
                .perform(post("/patient/add")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchPatient() throws Exception {
        String keyword = "Mel";
        List<PatientDTO> list = new ArrayList<>();

        when(patientService.searchPatient(keyword)).thenReturn(list);

        this.mockMvc
                .perform(get("/patients/search")
                        .param("keyword", keyword))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Integer id = 1;

        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");
        PatientDTO savedPatientDTO = new PatientDTO(1, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(patientService.updatePatient(id, patientDTO)).thenReturn(savedPatientDTO);

        this.mockMvc
                .perform(put("/patient/update/" + id)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPatient() throws Exception {
        Integer id = 1;

        PatientDTO patientDTO = new PatientDTO(null, "ADJOH", "Melanie", new Date(), "F", "2 Allée Jean de Joinville", "0124589522");

        when(patientService.getPatient(id)).thenReturn(patientDTO);

        this.mockMvc
                .perform(get("/patient/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePatient() throws Exception {
        Integer id = 1;

        doNothing().when(patientService).deletePatient(id);

        mockMvc.perform(delete("/patient/delete/"+id))
                .andExpect(status().isOk());

    }

}
