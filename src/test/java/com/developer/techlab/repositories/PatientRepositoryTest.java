package com.developer.techlab.repositories;

import com.developer.techlab.entities.Patient;
import com.developer.techlab.entities.enums.Sexe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void testSave(){
        Patient patient = new Patient();
        patient.setId(1);
        patient.setNom("hasna");
        patient.setEmail("hasna@gmail.com");
        patient.setTele("0654678762");
        patient.setSexe(Sexe.FEMININ);
        patientRepository.save(patient);

        assertEquals(patient.getId(), 1L);
        assertNotNull(patient);
    }

    @Test
    void testFindAll(){
        Patient patient = new Patient();
        patient.setNom("hasna");
        patient.setEmail("hasna@gmail.com");
        patient.setTele("0654678762");
        patient.setSexe(Sexe.FEMININ);
        Patient patient1 = new Patient();
        patient1.setNom("hasna");
        patient1.setEmail("hasna@gmail.com");
        patient1.setTele("0654678762");
        patient1.setSexe(Sexe.FEMININ);
        patientRepository.save(patient);
        patientRepository.save(patient1);

        List<Patient> patientList = patientRepository.findAll();

        assertNotNull(patientList);
        assertEquals(2,patientList.size());
    }

    @Test
    void testFindById(){
        Patient patient = new Patient();
        patient.setNom("hasna");
        patient.setEmail("hasna@gmail.com");
        patient.setTele("0654678762");
        patient.setSexe(Sexe.FEMININ);
        patientRepository.save(patient);

        Patient patient1 = patientRepository.findById(patient.getId()).get();

        assertNotNull(patient1);
        assertEquals(patient.getNom(), patient1.getNom());
    }

    @Test
    void testUpdate(){
        Patient patient = new Patient();
        patient.setNom("hasna");
        patient.setEmail("hasna@gmail.com");
        patient.setTele("0654678762");
        patient.setSexe(Sexe.FEMININ);
        patientRepository.save(patient);

        Patient patientSave = patientRepository.findById(patient.getId()).get();
        patientSave.setNom("updated");
        Patient patientUpdated = patientRepository.save(patientSave);

        assertNotNull(patientUpdated);
        assertEquals(patientUpdated.getNom(), "updated");
    }

    @Test
    void testDelete(){
        Patient patient = new Patient();
        patient.setNom("hasna");
        patient.setEmail("hasna@gmail.com");
        patient.setTele("0654678762");
        patient.setSexe(Sexe.FEMININ);
        patientRepository.save(patient);

        patientRepository.deleteById(patient.getId());

        Optional<Patient> patientReturn = patientRepository.findById(patient.getId());
        assertFalse(patientReturn.isPresent());
    }

}