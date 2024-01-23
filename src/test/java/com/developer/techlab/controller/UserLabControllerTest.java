package com.developer.techlab.controller;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.enums.Role;
import com.developer.techlab.service.UserLabService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
class UserLabControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private UserLabController userLabController;
    @Mock
    private UserLabService userLabService;

    @Test
    void createUserLab() throws Exception {
        UserLabDTO userLabDTO = new UserLabDTO(1L, "John Doe", "john@example.com", "password123", Role.TECHNICIEN);
        when(userLabService.saveUserLab(any(UserLabDTO.class))).thenReturn(userLabDTO);
        mockMvc = standaloneSetup(userLabController).build();
        mockMvc.perform(post("/userlabs/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userLabDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userLabDTO.getId()))
                .andExpect(jsonPath("$.nom").value(userLabDTO.getNom()))
                .andExpect(jsonPath("$.email").value(userLabDTO.getEmail()))
                .andDo(print());
    }

    @Test
    void getUserLab() throws Exception {
        long userLabId = 1L;
        UserLabDTO userLabDTO = new UserLabDTO(userLabId, "test", "test@example.com", "password", Role.TECHNICIEN);
        when(userLabService.getUserLabById(userLabId)).thenReturn(userLabDTO);
        mockMvc = standaloneSetup(userLabController).build();
        mockMvc.perform(get("/userlabs/{userLabId}", userLabId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        verify(userLabService, times(1)).getUserLabById(userLabId);
    }

    @Test
    void getAllUserLab() throws Exception {
        List<UserLabDTO> userLabDTOs = Arrays.asList(
                new UserLabDTO(1L, "test1", "test1@example.com", "password", Role.TECHNICIEN),
                new UserLabDTO(2L, "test2", "test2@example.com", "password", Role.TECHNICIEN)
        );
        when(userLabService.getAllUserLabs()).thenReturn(userLabDTOs);
        mockMvc = standaloneSetup(userLabController).build();
        mockMvc.perform(get("/userlabs/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userLabService, times(1)).getAllUserLabs();
    }

    @Test
    void deleteUserLab() throws Exception {
        long userLabId = 1L;
        mockMvc = standaloneSetup(userLabController).build();
        mockMvc.perform(delete("/userlabs/delete/{userLabId}", userLabId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userLabService, times(1)).deleteUserLab(userLabId);
    }

    @Test
    void updateUserLab() throws Exception {
        UserLabDTO updatedUserLabDTO = new UserLabDTO(1L,"UpdatedName","updated@email.com", "newpassword", Role.TECHNICIEN);
        when(userLabService.updateUserLab(any(Long.class), any(UserLabDTO.class))).thenReturn(updatedUserLabDTO);
        mockMvc = standaloneSetup(userLabController).build();
        mockMvc.perform(put("/userlabs/update/{userLabId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedUserLabDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}