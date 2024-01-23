package com.developer.techlab.controller;

import com.developer.techlab.DTO.PatientDTO;
import com.developer.techlab.entities.Echantillon;
import com.developer.techlab.entities.enums.Sexe;
import com.developer.techlab.entities.enums.StatutEchantillon;
import com.developer.techlab.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;
    @Mock
    PatientService patientService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPatient() throws Exception {
        List<Echantillon> echantillons = Arrays.asList(
                new Echantillon(1L, StatutEchantillon.EN_ATTENTE),
                new Echantillon(2L, StatutEchantillon.EN_ATTENTE));
        PatientDTO patientDTO = new PatientDTO(1L,"hasna","hasna@gmail.com","0555555555", Sexe.FEMININ,echantillons);
        when(patientService.savePatient(any(PatientDTO.class))).thenReturn(patientDTO);
//        mockMvc = standaloneSetup(patientController).build();
        mockMvc.perform(post("/patients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patientDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getPatient() throws Exception {
        List<Echantillon> echantillons = Arrays.asList(
                new Echantillon(1L,StatutEchantillon.EN_ATTENTE),
                new Echantillon(2L, StatutEchantillon.EN_ATTENTE));
        PatientDTO patientDTO = new PatientDTO(1L, "hasna", "hasna@gmail.com", "0555555555", Sexe.FEMININ, echantillons);
        when(patientService.getPatientById(anyLong())).thenReturn(patientDTO);
        mockMvc = standaloneSetup(patientController).build();
        mockMvc.perform(get("/patients/{patientId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllPatients() throws Exception {
        List<Echantillon> echantillons1 = Arrays.asList(
                new Echantillon(1L, StatutEchantillon.EN_ATTENTE),
                new Echantillon(2L, StatutEchantillon.EN_ATTENTE));
        PatientDTO patientDTO1 = new PatientDTO(1L, "hasna", "hasna@gmail.com", "0555555555", Sexe.FEMININ, echantillons1);

        List<Echantillon> echantillons2 = Arrays.asList(
                new Echantillon(3L, StatutEchantillon.EN_ATTENTE),
                new Echantillon(4L, StatutEchantillon.EN_ATTENTE));
        PatientDTO patientDTO2 = new PatientDTO(2L, "hsn", "hsn@gmail.com", "0666666666", Sexe.MASCULIN, echantillons2);

        List<PatientDTO> patients = Arrays.asList(patientDTO1, patientDTO2);
        when(patientService.getAllPatients()).thenReturn(patients);
        mockMvc = standaloneSetup(patientController).build();
        mockMvc.perform(get("/patients/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deletePatient() throws Exception {
        doNothing().when(patientService).deletePatient(1L);
        mockMvc = standaloneSetup(patientController).build();
        mockMvc.perform(delete("/patients/delete/{patientId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    void updatePatient() throws Exception {
//        long patientId = 1L;
//        List<Echantillon> echantillons = Arrays.asList(
//                new Echantillon(1L, StatutEchantillon.EN_ATTENTE),
//                new Echantillon(2L, StatutEchantillon.EN_ATTENTE));
//        PatientDTO updatedPatientDTO = new PatientDTO(1L, "hasna", "hasna@gmail.com", "0555555555", Sexe.FEMININ, echantillons);
//        when(patientService.updatePatient(patientId, updatedPatientDTO)).thenReturn(updatedPatientDTO);
//        mockMvc = standaloneSetup(patientController).build();
//        mockMvc.perform(put("/update/{patientId}", patientId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedPatientDTO)))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}