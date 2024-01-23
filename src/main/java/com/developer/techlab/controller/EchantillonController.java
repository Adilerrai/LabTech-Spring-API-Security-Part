package com.developer.techlab.controller;

import com.developer.techlab.DTO.EchantillonDTO;
import com.developer.techlab.service.EchantillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/echantillons")
public class EchantillonController {

    @Autowired
    private EchantillonService echantillonService;

    @PostMapping("/create")
    public ResponseEntity<EchantillonDTO> createEchantillon(@RequestBody EchantillonDTO echantillonDTO) {
        EchantillonDTO createdEchantillon = echantillonService.saveEchantillon(echantillonDTO);
        return ResponseEntity.ok(createdEchantillon);
    }

    @GetMapping("/{echantillonId}")
    public ResponseEntity<EchantillonDTO> getEchantillon(@PathVariable long echantillonId) {
        EchantillonDTO echantillon = echantillonService.getEchantillonById(echantillonId);
        return (echantillon != null) ? ResponseEntity.ok(echantillon) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<EchantillonDTO>> getAllEchantillons() {
        List<EchantillonDTO> echantillons = echantillonService.getAllEchantillons();
        return ResponseEntity.ok(echantillons);
    }

    @DeleteMapping("/delete/{echantillonId}")
    public ResponseEntity<Void> deleteEchantillon(@PathVariable long echantillonId) {
        echantillonService.deleteEchantillon(echantillonId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{echantillonId}")
    public ResponseEntity<EchantillonDTO> updateEchantillon(
            @PathVariable long echantillonId,
            @RequestBody EchantillonDTO updatedEchantillonDTO) {
        EchantillonDTO updatedEchantillon = echantillonService.updateEchantillon(echantillonId, updatedEchantillonDTO);
        return (updatedEchantillon != null) ? ResponseEntity.ok(updatedEchantillon) : ResponseEntity.notFound().build();
    }
}
