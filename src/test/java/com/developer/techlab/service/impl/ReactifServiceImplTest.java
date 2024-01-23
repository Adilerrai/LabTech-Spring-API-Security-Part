package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.ReactifDTO;
import com.developer.techlab.entities.Reactif;
import com.developer.techlab.repositories.ReactifRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReactifServiceImplTest {

    @Mock
    ReactifRepository reactifRepository;
    ModelMapper modelMapper;
    @InjectMocks
    ReactifServiceImpl reactifServiceImpl;

    @BeforeEach
    void setUp() {
        reactifRepository = mock(ReactifRepository.class);
        modelMapper = new ModelMapper();
        reactifServiceImpl = new ReactifServiceImpl(reactifRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveReactif() {
        ReactifDTO reactifDTO = new ReactifDTO();
        reactifDTO.setId(1L);
        reactifDTO.setLibelle("reactif"); reactifDTO.setDate_exp(LocalDate.now());
        reactifDTO.setQuantite(645); reactifDTO.setFournisseur("ayoub");
        Reactif reactif = new Reactif();
        reactif.setId(1L);
        reactif.setLibelle("reactif"); reactif.setDate_exp(LocalDate.now());
        reactif.setQuantite(645); reactif.setFournisseur("ayoub");

        when(reactifRepository.save(any(Reactif.class))).thenReturn(reactif);

        ReactifDTO savedReactifDTO = reactifServiceImpl.saveReactif(reactifDTO);

        assertEquals(reactifDTO.getId(),savedReactifDTO.getId());
    }

    @Test
    void getReactifById() {
        Reactif reactif = new Reactif();
        reactif.setId(1L);
        reactif.setLibelle("reactif"); reactif.setDate_exp(LocalDate.now());
        reactif.setQuantite(645); reactif.setFournisseur("ayoub");

        when(reactifRepository.findById(anyLong())).thenReturn(Optional.of(reactif));

        ReactifDTO foundReactifDTO = reactifServiceImpl.getReactifById(1L);

        assertNotNull(foundReactifDTO);
        assertEquals(reactif.getId(), foundReactifDTO.getId());
    }

    @Test
    void getAllReactifs() {
        List<Reactif> reactifList = new ArrayList<>();
        Reactif reactif = new Reactif();
        reactif.setId(1L);
        reactif.setLibelle("reactif"); reactif.setDate_exp(LocalDate.now());
        reactif.setQuantite(645); reactif.setFournisseur("ayoub");
        reactifList.add(reactif);
        Reactif reactif1 = new Reactif();
        reactif1.setId(1L);
        reactif1.setLibelle("reactif"); reactif1.setDate_exp(LocalDate.now());
        reactif1.setQuantite(645); reactif1.setFournisseur("ayoub");
        reactifList.add(reactif1);

        when(reactifRepository.findAll()).thenReturn(reactifList);

        List<ReactifDTO> reactifDTOList = reactifServiceImpl.getAllReactifs();

        assertNotNull(reactifDTOList);
        assertEquals(2,reactifList.size());
    }

    @Test
    void deleteReactif() {
        reactifServiceImpl.deleteReactif(1L);
        verify(reactifRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateReactif() {
        ReactifDTO reactifDTO = new ReactifDTO();
        reactifDTO.setId(1L);
        reactifDTO.setLibelle("reactif"); reactifDTO.setDate_exp(LocalDate.now());
        reactifDTO.setQuantite(645); reactifDTO.setFournisseur("ayoub");
        Reactif reactif = new Reactif();
        reactif.setId(1L);
        reactif.setLibelle("reactif"); reactif.setDate_exp(LocalDate.now());
        reactif.setQuantite(645); reactif.setFournisseur("ayoub");

        when(reactifRepository.findById(anyLong())).thenReturn(Optional.of(reactif));
        when(reactifRepository.save(any(Reactif.class))).thenReturn(reactif);

        ReactifDTO updatedReactifDTO = reactifServiceImpl.updateReactif(1L,reactifDTO);

        assertNotNull(updatedReactifDTO);
        assertEquals(reactifDTO.getId(), updatedReactifDTO.getId());
    }

    @Test
    void getReactifQuantity() {

        Reactif mockReactif = new Reactif();
        mockReactif.setId(1L);
        mockReactif.setQuantite(100);

        when(reactifRepository.findById(1L)).thenReturn(java.util.Optional.of(mockReactif));

        int actualQuantity = reactifServiceImpl.getReactifQuantity(1L);

        assertEquals(100, actualQuantity, "Quantities should match");
    }
}