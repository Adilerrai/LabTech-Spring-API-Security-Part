package com.developer.techlab.service.impl;

import com.developer.techlab.entities.Patient;
import com.developer.techlab.repositories.PatientRepository;
import com.developer.techlab.service.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    private PatientService patientService;

    @BeforeEach
    void setUp() {
        Patient patient = new Patient();
        patient.setNom("adil");


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void savePatient() {

    }

    @Test
    void getPatientById() {
    }

    @Test
    void getAllPatients() {
    }

    @Test
    void deletePatient() {
    }

    @Test
    void updatePatient() {
    }
}