package com.developer.techlab.service;

import com.developer.techlab.DTO.AnalyseDTO;
import com.developer.techlab.DTO.TesteDTO;
import com.developer.techlab.entities.Analyse;
import com.developer.techlab.entities.Teste;
import com.developer.techlab.repositories.AnalyseRepository;
import com.developer.techlab.repositories.EchantillonRepository;
import com.developer.techlab.repositories.TesteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnalyseServiceTest {

    @Mock
    private AnalyseRepository analyseRepository;

    @Mock
    private EchantillonRepository echantillonRepository;
    @Mock
    private TesteRepository testeRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private AnalyseService analyseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAnalyse() {

    }
}
