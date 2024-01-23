package com.developer.techlab.controller;

import com.developer.techlab.DTO.PatientDTO;
import com.developer.techlab.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.savePatient(patientDTO);
        return ResponseEntity.ok(createdPatient);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable long patientId) {
        PatientDTO patient = patientService.getPatientById(patientId);
        return (patient != null) ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable long patientId,
            @RequestBody PatientDTO updatedPatientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(patientId, updatedPatientDTO);
        return (updatedPatient != null) ? ResponseEntity.ok(updatedPatient) : ResponseEntity.notFound().build();
    }
}
