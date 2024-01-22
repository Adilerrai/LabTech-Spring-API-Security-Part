
package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.PatientDTO;
import com.developer.techlab.entities.Patient;
import com.developer.techlab.repositories.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

class PatientServiceImplTest {

    @Mock
    PatientRepository patientRepository;

    ModelMapper modelMapper;

    @InjectMocks
    PatientServiceImpl patientServiceImpl;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        modelMapper = new ModelMapper();
        patientServiceImpl = new PatientServiceImpl(patientRepository, modelMapper);

}

    @AfterEach
    void tearDown() {
    }


    @Test
    void savePatient() {
        // Arrange
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setNom("adil");
        // Assume that the modelMapper.map() method will return a Patient object
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("adil");
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Act
        PatientDTO savedPatientDTO = patientServiceImpl.savePatient(patientDTO);

        // Assert
        assertEquals(patientDTO.getId(), savedPatientDTO.getId());
        assertEquals(patientDTO.getNom(), savedPatientDTO.getNom());
    }



    @Test
    void getPatientById() {
        // Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("adil");
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        // Act
        PatientDTO foundPatientDTO = patientServiceImpl.getPatientById(1L);

        // Assert
        assertNotNull(foundPatientDTO);
        assertEquals(patient.getId(), foundPatientDTO.getId());
        assertEquals(patient.getNom(), foundPatientDTO.getNom());
    }

    @Test
    void getAllPatients() {
        // Arrange
        List<Patient> patients = new ArrayList<>();
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setNom("adil");
        patients.add(patient1);

        Patient patient2 = new Patient();
        patient2.setId(2L);
        patient2.setNom("adil");
        patients.add(patient2);

        when(patientRepository.findAll()).thenReturn(patients);

        // Act
        List<PatientDTO> patientDTOs = patientServiceImpl.getAllPatients();

        // Assert
        assertNotNull(patientDTOs);
        assertEquals(2, patientDTOs.size());
    }

    @Test
    void deletePatient() {
        // No need to arrange anything for this test

        // Act
        patientServiceImpl.deletePatient(1L);

        // Assert
        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void updatePatient() {
        // Arrange
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setNom("adil");
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("adil");
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Act
        PatientDTO updatedPatientDTO = patientServiceImpl.updatePatient(1L, patientDTO);

        // Assert
        assertNotNull(updatedPatientDTO);
        assertEquals(patientDTO.getId(), updatedPatientDTO.getId());
        assertEquals(patientDTO.getNom(), updatedPatientDTO.getNom());
    }
}

