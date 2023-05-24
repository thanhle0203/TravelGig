package com.thanhle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.thanhle.domain.Insured;
import com.thanhle.service.InsuredService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/insured/save")
public class InsuredInfoController {
    private final InsuredService insuredService;

    @Autowired
    public InsuredInfoController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }
    
    @PostMapping
    public ResponseEntity<Insured> saveInsured(@RequestBody Insured insured) {
    	Insured savedInsured = insuredService.saveInsured(insured);
        return new ResponseEntity<>(savedInsured, HttpStatus.CREATED);
        
    }
 
    /*
    
    
    
    @PostMapping("/submitDocument")
    public ResponseEntity<Insured> submitDocument(@RequestParam("driverLicense") MultipartFile file,
                                                  @RequestParam("jsonData") String jsonData) {
        try {
            // Convert JSON data to Insured object
            ObjectMapper objectMapper = new ObjectMapper();
            Insured insured = objectMapper.readValue(jsonData, Insured.class);

            // Set the driver license data
            insured.setDriverLicense(file.getBytes());

            Insured savedInsured = insuredService.saveInsured(insured);
            return new ResponseEntity<>(savedInsured, HttpStatus.CREATED);
        } catch (IOException e) {
            // Handle the exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping
    public ResponseEntity<List<Insured>> getAllInsured() {
        List<Insured> insuredList = insuredService.getAllInsured();
        return new ResponseEntity<>(insuredList, HttpStatus.OK);
    }
    */
}
