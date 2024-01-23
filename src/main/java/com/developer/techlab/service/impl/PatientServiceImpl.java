package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.EchantillonDTO;
import com.developer.techlab.DTO.PatientDTO;
import com.developer.techlab.entities.Echantillon;
import com.developer.techlab.entities.Patient;
import com.developer.techlab.repositories.EchantillonRepository;
import com.developer.techlab.repositories.PatientRepository;
import com.developer.techlab.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

     PatientRepository patientRepository;
     ModelMapper modelMapper;
//     EchantillonRepository echantillonRepository;



    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
//        List<Echantillon> echantillons = patient.getEchantillons();
        Patient savedPatient = patientRepository.save(patient);
//        for(Echantillon echantillon : echantillons){
//            echantillon.setPatient(savedPatient);
//            echantillonRepository.save(echantillon);
//        }
        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientById(long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        return (patient != null) ? modelMapper.map(patient, PatientDTO.class) : null;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePatient(long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public PatientDTO updatePatient(long patientId, PatientDTO updatedPatientDTO) {
        Patient existingPatient = patientRepository.findById(patientId).orElse(null);
        if (existingPatient != null) {
            modelMapper.map(updatedPatientDTO, existingPatient);
            return modelMapper.map(patientRepository.save(existingPatient), PatientDTO.class);
        }
        return null;
    }
}