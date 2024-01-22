package com.developer.techlab.controller;

import com.developer.techlab.DTO.ReactifDTO;
import com.developer.techlab.service.ReactifService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
class ReactifControllerTest {

    @InjectMocks
    private ReactifController reactifController;
    @Mock
    private ReactifService reactifService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createReactif() throws Exception {
        ReactifDTO reactifDTO = new ReactifDTO(1L, "Reactif Libelle", 10, LocalDate.of(2025, 1, 21),
                "Supplier ABC");
        when(reactifService.saveReactif(any(ReactifDTO.class))).thenReturn(reactifDTO);
        mockMvc = standaloneSetup(reactifController).build();
        mockMvc.perform(post("/api/reactifs/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reactifDTO)))
                        .andExpect(status().isOk());
    }

    @Test
    void getReactif() throws Exception {
        long reactifId = 1L;
        ReactifDTO reactifDTO = new ReactifDTO(reactifId, "Reactif Libelle", 10, LocalDate.of(2025, 1, 21), "Supplier ABC");
        when(reactifService.getReactifById(anyLong())).thenReturn(reactifDTO);
        mockMvc = standaloneSetup(reactifController).build();
        mockMvc.perform(get("/api/reactifs/{reactifId}", reactifId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllReactifs() throws Exception {
        List<ReactifDTO> reactifs = Arrays.asList(
                new ReactifDTO(1L, "Reactif 1", 10, LocalDate.of(2025, 1, 21), "four A"),
                new ReactifDTO(2L, "Reactif 2", 15, LocalDate.of(2025, 2, 15), "four B"));
        when(reactifService.getAllReactifs()).thenReturn(reactifs);
        mockMvc = standaloneSetup(reactifController).build();
        mockMvc.perform(get("/api/reactifs/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteReactif() throws Exception {
        doNothing().when(reactifService).deleteReactif(1L);
        mockMvc = standaloneSetup(reactifController).build();
        mockMvc.perform(delete("/api/reactifs/delete/{reactifId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(reactifService).deleteReactif(1L);
    }

    @Test
    void updateReactif() throws Exception {
        ReactifDTO updatedReactifDTO = new ReactifDTO(1L, "Updated Libelle", 10, LocalDate.of(2025, 1, 1), "Updated Fournisseur");
        when(reactifService.updateReactif(eq(1L), any(ReactifDTO.class))).thenReturn(updatedReactifDTO);
        mockMvc = standaloneSetup(reactifController).build();
        mockMvc.perform(put("/update/{reactifId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedReactifDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        verify(reactifService).updateReactif(eq(1L), any(ReactifDTO.class));
    }
}