package com.developer.techlab.controller;

import com.developer.techlab.DTO.AnalyseDTO;
import com.developer.techlab.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyses")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;

    @PostMapping("/create")
    public ResponseEntity<AnalyseDTO> createAnalyse(@RequestBody AnalyseDTO analyseDTO) {
        AnalyseDTO createdAnalyse = analyseService.saveAnalyse(analyseDTO);
        return ResponseEntity.ok(createdAnalyse);
    }

    @GetMapping("/{analyseId}")
    public ResponseEntity<AnalyseDTO> getAnalyse(@PathVariable long analyseId) {
        AnalyseDTO analyse = analyseService.getAnalyseById(analyseId);
        return (analyse != null) ? ResponseEntity.ok(analyse) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnalyseDTO>> getAllAnalyses() {
        List<AnalyseDTO> analyses = analyseService.getAllAnalyses();
        return ResponseEntity.ok(analyses);
    }

    @DeleteMapping("/delete/{analyseId}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable long analyseId) {
        analyseService.deleteAnalyse(analyseId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{analyseId}")
    public ResponseEntity<AnalyseDTO> updateAnalyse(
            @PathVariable long analyseId,
            @RequestBody AnalyseDTO updatedAnalyseDTO) {
        AnalyseDTO updatedAnalyse = analyseService.updateAnalyse(analyseId, updatedAnalyseDTO);
        return (updatedAnalyse != null) ? ResponseEntity.ok(updatedAnalyse) : ResponseEntity.notFound().build();
    }
}
