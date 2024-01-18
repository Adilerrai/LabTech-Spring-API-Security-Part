package com.developer.techlab.service;

import com.developer.techlab.DTO.PatientDTO;
import com.developer.techlab.entities.Patient;

import java.util.List;

public interface PatientService {
    PatientDTO savePatient(PatientDTO patientDTO);

    PatientDTO getPatientById(long patientId);

    List<PatientDTO> getAllPatients();

    void deletePatient(long patientId);

    PatientDTO updatePatient(long patientId, PatientDTO updatedPatientDTO);
}
