package com.developer.techlab.controller;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.service.UserLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlabs")
public class UserLabController {

    @Autowired
    private UserLabService userLabService;

    @PostMapping("/create")
    public ResponseEntity<UserLabDTO> createUserLab(@RequestBody UserLabDTO userLabDTO){
        UserLabDTO createUserLab = userLabService.saveUserLab(userLabDTO);
        return ResponseEntity.ok(createUserLab);
    }

    @GetMapping("/{userLabId}")
    public ResponseEntity<UserLabDTO> getUserLab(@PathVariable long userLabId){
        UserLabDTO userLab = userLabService.getUserLabById(userLabId);
        return (userLab != null) ? ResponseEntity.ok(userLab) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserLabDTO>> getAllUserLab(){
        List<UserLabDTO> userLabDTOS = userLabService.getAllUserLabs();
        return ResponseEntity.ok(userLabDTOS);
    }

    @DeleteMapping("/delete/{userLabId}")
    public ResponseEntity<Void> deleteUserLab(@PathVariable long userLabId){
        userLabService.deleteUserLab(userLabId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{userLabId}")
    public ResponseEntity<UserLabDTO> updateUserLab(@PathVariable long userLabId, @RequestBody UserLabDTO updatedUserLabDTO){
        UserLabDTO updatedUserLab = userLabService.updateUserLab(userLabId, updatedUserLabDTO);
        return (updatedUserLab != null) ? ResponseEntity.ok(updatedUserLab) : ResponseEntity.notFound().build();
    }
}
