package com.developer.techlab.controller;

import com.developer.techlab.DTO.ReactifDTO;
import com.developer.techlab.service.ReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactifs")
public class ReactifController {

    @Autowired
    private ReactifService reactifService;

    @PostMapping("/create")
    public ResponseEntity<ReactifDTO> createReactif(@RequestBody ReactifDTO reactifDTO) {
        ReactifDTO createdReactif = reactifService.saveReactif(reactifDTO);
        return ResponseEntity.ok(createdReactif);
    }

    @GetMapping("/{reactifId}")
    public ResponseEntity<ReactifDTO> getReactif(@PathVariable long reactifId) {
        ReactifDTO reactifDTO = reactifService.getReactifById(reactifId);
        return (reactifDTO != null) ? ResponseEntity.ok(reactifDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReactifDTO>> getAllReactifs() {
        List<ReactifDTO> reactifs = reactifService.getAllReactifs();
        return ResponseEntity.ok(reactifs);
    }

    @DeleteMapping("/delete/{reactifId}")
    public ResponseEntity<Void> deleteReactif(@PathVariable long reactifId) {
        reactifService.deleteReactif(reactifId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{reactifId}")
    public ResponseEntity<ReactifDTO> updateReactif(
            @PathVariable long reactifId,
            @RequestBody ReactifDTO updatedReactifDTO) {
        ReactifDTO updatedReactif = reactifService.updateReactif(reactifId, updatedReactifDTO);
        return (updatedReactif != null) ? ResponseEntity.ok(updatedReactif) : ResponseEntity.notFound().build();
    }
}
