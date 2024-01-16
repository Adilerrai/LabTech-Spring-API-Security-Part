package com.developer.techlab.controllers;

import com.developer.techlab.entities.Analyse;

import com.developer.techlab.services.IAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analyses")
public class AnalyseController {

    @Autowired
    private IAnalyseService analyseService;

    @GetMapping("/ongoing")
    public ResponseEntity<List<Analyse>> getAllOngoingAnalyses() {
        List<Analyse> ongoingAnalyses = analyseService.getAllOngoingAnalyses();
        return new ResponseEntity<>(ongoingAnalyses, HttpStatus.OK);
    }
}
