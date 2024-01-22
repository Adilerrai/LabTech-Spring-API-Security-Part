package com.developer.techlab.controller;

import com.developer.techlab.DTO.AnalyseDTO;
import com.developer.techlab.DTO.AnalyseDetailsDTO;
import com.developer.techlab.DTO.TesteDTO;
import com.developer.techlab.service.AnalyseService;
import com.developer.techlab.service.ReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyses")
public class AnalyseController {

    @Autowired
    private AnalyseService analyseService;
    @Autowired
    private ReactifService reactifService;

    @PostMapping("/create")
    public ResponseEntity<AnalyseDTO> createAnalyse(@RequestBody AnalyseDTO analyseDTO) {
        long reactifId = analyseDTO.getReactif().getId();
        int reactifQuantity = reactifService.getReactifQuantity(reactifId);
        if (reactifQuantity < 5) {
            System.out.println("Low reactif quantity! Please add more reactifs.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
    @GetMapping("/ongoing")
    public ResponseEntity<List<AnalyseDTO>> getOngoingAnalyses() {
        List<AnalyseDTO> ongoingAnalyses = analyseService.getOngoingAnalyses();
        return ResponseEntity.ok(ongoingAnalyses);
    }

    @GetMapping("/{analyseId}/test-results")
    public ResponseEntity<List<TesteDTO>> getTestResultsForAnalyse(@PathVariable long analyseId) {
        List<TesteDTO> getTestResultsForAnalyse = analyseService.getTestResultsForAnalyse(analyseId);
        return ResponseEntity.ok(getTestResultsForAnalyse);
    }

//    @PostMapping("/createAnalyse")
//    public ResponseEntity<AnalyseDTO> createAnalyse(@RequestBody AnalyseDetailsDTO analyseDetailsDTO) {
//        AnalyseDTO createdAnalyse = analyseService.create(analyseDetailsDTO);
//        if (createdAnalyse != null) {
//            return ResponseEntity.ok(createdAnalyse);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }


}
