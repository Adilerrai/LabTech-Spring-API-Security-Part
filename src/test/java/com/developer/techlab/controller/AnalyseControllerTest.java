//package com.developer.techlab.controller;
//
//import com.developer.techlab.DTO.*;
//import com.developer.techlab.entities.Teste;
//import com.developer.techlab.entities.UserLab;
//import com.developer.techlab.service.AnalyseService;
//import com.developer.techlab.service.ReactifService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AnalyseControllerTest {
//
//    @InjectMocks
//    private AnalyseController analyseController;
//    @Mock
//    private ReactifService reactifService;
//    @Mock
//    private AnalyseService analyseService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testCreateAnalyse() throws Exception {
//        EchantillonDTO echantillonDTO = new EchantillonDTO(5);
//        UserLab userLabDTO = new UserLab(1);
//        PatientDTO patientDTO = new PatientDTO(1);
//        ReactifDTO reactifDTO = new ReactifDTO(1);
//        AnalyseDTO inputAnalyseDTO = new AnalyseDTO("Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillon, userLab, patient, reactif);
//        mockMvc = standaloneSetup(analyseController).build();
//        mockMvc.perform(post("/analyses/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(inputAnalyseDTO)))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(reactifService, times(1)).getReactifQuantity(anyLong());
//        verify(analyseService, times(1)).saveAnalyse(any(AnalyseDTO.class));
//    }
//
//    @Test
//    public void testGetAnalyse() throws Exception {
//        long analyseId = 5L;
//        EchantillonDTO echantillonDTO = new EchantillonDTO(5);
//        UserLabDTO userLabDTO = new UserLabDTO(2);
//        PatientDTO patientDTO = new PatientDTO(1);
//        ReactifDTO reactifDTO = new ReactifDTO(1);
//        AnalyseDTO expectedAnalyseDTO = new AnalyseDTO(5L, "Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillonDTO, userLabDTO, patientDTO, reactifDTO);
//        when(analyseService.getAnalyseById(analyseId)).thenReturn(expectedAnalyseDTO);
//        mockMvc = standaloneSetup(analyseController).build();
//        mockMvc.perform(get("/analyses/{analyseId}", analyseId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//        verify(analyseService, times(1)).getAnalyseById(analyseId);
//    }
//
//    @Test
//    void testGetAllAnalyses() throws Exception {
//        // Arrange
//        EchantillonDTO echantillonDTO = new EchantillonDTO(5);
//        UserLabDTO userLabDTO = new UserLabDTO(2);
//        PatientDTO patientDTO = new PatientDTO(1);
//        ReactifDTO reactifDTO = new ReactifDTO(1);
//        List<AnalyseDTO> expectedAnalyses = Arrays.asList(
//                new AnalyseDTO(10L, "Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillonDTO, userLabDTO, patientDTO, reactifDTO),
//                new AnalyseDTO(11L, "Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillonDTO, userLabDTO, patientDTO, reactifDTO));
//        when(analyseService.getAllAnalyses()).thenReturn(expectedAnalyses);
//        mockMvc = standaloneSetup(analyseController).build();
//        mockMvc.perform(get("/analyses/all")
//                 .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print());
//    }
//
//    @Test
//    void testDeleteAnalyse() throws Exception {
//        long analyseId = 1L;
//        doNothing().when(analyseService).deleteAnalyse(analyseId);
//
//        mockMvc = standaloneSetup(analyseController).build();
//        mockMvc.perform(delete("/analyses/delete/{analyseId}", analyseId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    void testUpdateAnalyse() throws Exception{
//        long analyseId = 5L;
//        EchantillonDTO echantillonDTO = new EchantillonDTO(5);
//        UserLabDTO userLabDTO = new UserLabDTO(2);
//        PatientDTO patientDTO = new PatientDTO(1);
//        ReactifDTO reactifDTO = new ReactifDTO(1);
//        AnalyseDTO updatedAnalyseDTO = new AnalyseDTO(5L, "Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillonDTO, userLabDTO, patientDTO, reactifDTO);
//        when(analyseService.updateAnalyse(eq(analyseId), any(AnalyseDTO.class))).thenReturn(updatedAnalyseDTO);
//        mockMvc = standaloneSetup(analyseController).build();
//        mockMvc.perform(put("/analyses/update/{analyseId}", analyseId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedAnalyseDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andDo(print());
//    }
//}
