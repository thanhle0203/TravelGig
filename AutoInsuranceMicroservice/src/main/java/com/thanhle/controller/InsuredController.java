package com.thanhle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhle.domain.Document;
import com.thanhle.domain.Insured;
import com.thanhle.service.DocumentService;
import com.thanhle.service.InsuredService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282") // Allow requests from this origin
@RequestMapping("/api/insured")
public class InsuredController {

    private final InsuredService insuredService;
    private final DocumentService documentService;

    @Autowired
    public InsuredController(InsuredService insuredService, DocumentService documentService) {
        this.insuredService = insuredService;
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Insured> saveInsured(
            @RequestParam("driverLicense") MultipartFile driverLicense,
            @RequestParam("insured") String insuredJson) {
        try {
            byte[] fileData = driverLicense.getBytes();
            Document document = new Document(fileData, driverLicense.getOriginalFilename());
            Document savedDocument = documentService.saveDocument(document);

            // Deserialize insured JSON
            Insured insured = new ObjectMapper().readValue(insuredJson, Insured.class);
            insured.setDocument(savedDocument);
            Insured savedInsured = insuredService.saveInsured(insured);

            return new ResponseEntity<>(savedInsured, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Insured>> getAllInsured() {
        List<Insured> insuredList = insuredService.getAllInsured();
        return new ResponseEntity<>(insuredList, HttpStatus.OK);
    }
}
