package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.*;
import com.developer.techlab.entities.*;
import com.developer.techlab.repositories.AnalyseRepository;
import com.developer.techlab.repositories.EchantillonRepository;
import com.developer.techlab.repositories.TesteRepository;
import com.developer.techlab.service.AnalyseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnalyseServiceTest {

    @Mock
    AnalyseRepository analyseRepository;
    @Mock
    EchantillonRepository echantillonRepository;
    @Mock
    TesteRepository testeRepository;
    @Mock
    EntityManager entityManager;
    ModelMapper modelMapper;

    @InjectMocks
    AnalyseServiceImpl analyseServiceImpl;

    @BeforeEach
    void setUp() {
        analyseRepository = mock(AnalyseRepository.class);
        echantillonRepository = mock(EchantillonRepository.class);
        testeRepository = mock(TesteRepository.class);
        entityManager = mock(EntityManager.class);
        modelMapper = new ModelMapper();
        analyseServiceImpl = new AnalyseServiceImpl(analyseRepository,testeRepository, echantillonRepository,modelMapper,entityManager);
    }

    @Test
    void testSaveAnalyse() {
        Echantillon echantillon = new Echantillon(5);
        UserLab userLab = new UserLab(1);
        Patient patient = new Patient(1);
        Reactif reactif = new Reactif(1);
        AnalyseDTO inputAnalyseDTO = new AnalyseDTO("Analyse Libelle", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillon, userLab, patient, reactif);

        Analyse analyse = new Analyse();
        analyse.setId(1L);

        when(echantillonRepository.findById(5L)).thenReturn(java.util.Optional.of(echantillon));
        when(analyseRepository.save(any(Analyse.class))).thenReturn(analyse);

        AnalyseDTO savedAnalyseDTO = analyseServiceImpl.saveAnalyse(inputAnalyseDTO);

        assertNotNull(savedAnalyseDTO);
        assertEquals(1L, savedAnalyseDTO.getId());
    }

    @Test
    void testGetAnalyseById(){
        Analyse analyse = new Analyse();
        analyse.setId(1L);
        analyse.setLibelle("Analyse");

        AnalyseDTO expectedAnalyseDTO = new AnalyseDTO();
        expectedAnalyseDTO.setId(1L);
        expectedAnalyseDTO.setLibelle("Analyse");

        when(analyseRepository.findById(1L)).thenReturn(Optional.of(analyse));

        AnalyseDTO actualAnalyseDTO = analyseServiceImpl.getAnalyseById(1L);

        assertNotNull(actualAnalyseDTO);
        assertEquals(expectedAnalyseDTO.getId(), actualAnalyseDTO.getId());
        assertEquals(expectedAnalyseDTO.getLibelle(), actualAnalyseDTO.getLibelle());
    }

    @Test
    void testGetAllAnalyses(){
        Echantillon echantillon = new Echantillon(5);
        UserLab userLab = new UserLab(1);
        Patient patient = new Patient(1);
        List<Analyse> mockAnalyses = Arrays.asList(
                new Analyse(1,"Analyse", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillon, userLab, patient),
                new Analyse(1,"Analyse", LocalDate.of(2024,10,21), LocalDate.of(2024,12,21), Arrays.asList(new Teste()), echantillon, userLab, patient)
        );

        when(analyseRepository.findAll()).thenReturn(mockAnalyses);

        List<AnalyseDTO> actualAnalyseDTOs = analyseServiceImpl.getAllAnalyses();

        assertNotNull(actualAnalyseDTOs);
        assertEquals(mockAnalyses.size(), actualAnalyseDTOs.size());

        for (int i = 0; i < mockAnalyses.size(); i++) {
            AnalyseDTO expectedDTO = modelMapper.map(mockAnalyses.get(i), AnalyseDTO.class);
            AnalyseDTO actualDTO = actualAnalyseDTOs.get(i);

            assertNotNull(actualDTO);
            assertEquals(expectedDTO.getId(), actualDTO.getId());
            assertEquals(expectedDTO.getLibelle(), actualDTO.getLibelle());
        }
    }

    @Test
    void testDeleteAnalyse(){
        long analyseId = 1L;
        analyseServiceImpl.deleteAnalyse(analyseId);
        verify(analyseRepository, times(1)).deleteById(analyseId);
    }

    @Test
    void testGpdateAnalyse(){
        Echantillon echantillon = new Echantillon(5);
        UserLab userLab = new UserLab(1);
        Patient patient = new Patient(1);
        Reactif reactif = new Reactif(1);
        AnalyseDTO updatedAnalyseDTO = new AnalyseDTO("Updated Analyse", LocalDate.now(), LocalDate.now().plusDays(7), Arrays.asList(new Teste()), echantillon, userLab, patient, reactif);

        Analyse existingAnalyse = new Analyse();
        existingAnalyse.setId(1L);

        Optional<Analyse> optionalAnalyse = Optional.of(existingAnalyse);

        when(analyseRepository.findById(1L)).thenReturn(optionalAnalyse);
        when(analyseRepository.save(existingAnalyse)).thenReturn(existingAnalyse);

        AnalyseDTO updatedDTO = analyseServiceImpl.updateAnalyse(1L, updatedAnalyseDTO);

        verify(analyseRepository, times(1)).findById(1L);

        verify(analyseRepository, times(1)).save(existingAnalyse);

        assertNotNull(updatedDTO);
        assertEquals("Updated Analyse", updatedDTO.getLibelle());
        assertEquals(LocalDate.now(), updatedDTO.getDate_debut());
        assertEquals(LocalDate.now().plusDays(7), updatedDTO.getDate_fin());
    }

    @Test
    void testGetOngoingAnalyses(){

        Echantillon echantillon = new Echantillon(5);
        UserLab userLab = new UserLab(1);
        Patient patient = new Patient(1);
        Reactif reactif = new Reactif(1);
        List<Analyse> ongoingAnalyses = Arrays.asList(
                new Analyse(1,"Ongoing Analyse 1", LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), Arrays.asList(new Teste()), echantillon, userLab, patient),
                new Analyse(2,"Ongoing Analyse 2", LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), Arrays.asList(new Teste()), echantillon, userLab, patient)
        );

        when(analyseRepository.findOngoingAnalyses(LocalDate.now())).thenReturn(ongoingAnalyses);

        List<AnalyseDTO> ongoingAnalysesDTO = analyseServiceImpl.getOngoingAnalyses();

        verify(analyseRepository, times(1)).findOngoingAnalyses(LocalDate.now());

        assertEquals(2, ongoingAnalysesDTO.size());

        AnalyseDTO firstOngoingDTO = ongoingAnalysesDTO.get(0);
        assertEquals(1L, firstOngoingDTO.getId());
        assertEquals("Ongoing Analyse 1", firstOngoingDTO.getLibelle());
        assertEquals(LocalDate.now().minusDays(2), firstOngoingDTO.getDate_debut());
        assertEquals(LocalDate.now().plusDays(2), firstOngoingDTO.getDate_fin());

        AnalyseDTO secondOngoingDTO = ongoingAnalysesDTO.get(1);
        assertEquals(2L, secondOngoingDTO.getId());
        assertEquals("Ongoing Analyse 2", secondOngoingDTO.getLibelle());
        assertEquals(LocalDate.now().minusDays(1), secondOngoingDTO.getDate_debut());
        assertEquals(LocalDate.now().plusDays(3), secondOngoingDTO.getDate_fin());
    }
}
