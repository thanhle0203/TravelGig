package com.thanhle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.Document;
import com.thanhle.domain.Insured;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.AutoInsuranceRepository;
import com.thanhle.repository.VehicleRepository;
import com.thanhle.service.DocumentService;
import com.thanhle.service.InsuredService;
import com.thanhle.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api/insured")
public class InsuredController {

    private final InsuredService insuredService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;
    private final DocumentService documentService;
    private final AutoInsuranceRepository autoInsuranceRepository;

    @Autowired
    public InsuredController(InsuredService insuredService, DocumentService documentService,
                             AutoInsuranceRepository autoInsuranceRepository, VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.insuredService = insuredService;
        this.documentService = documentService;
        this.autoInsuranceRepository = autoInsuranceRepository;
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Insured> saveInsured(
            @RequestParam(value = "driverLicense", required = false) MultipartFile driverLicense,
            @RequestParam("insured") String insuredJson,
            @RequestParam(value = "autoInsuranceId", required = false) Long autoInsuranceId,
            @RequestParam(value = "vehicleId", required = false) Long vehicleId
            ) {
        try {
            byte[] fileData = (driverLicense != null) ? driverLicense.getBytes() : null;
            Document document = (fileData != null) ? new Document(fileData, driverLicense.getOriginalFilename()) : null;
            Document savedDocument = (document != null) ? documentService.saveDocument(document) : null;

            // Deserialize insured JSON
            ObjectMapper objectMapper = new ObjectMapper();
            Insured insured = objectMapper.readValue(insuredJson, Insured.class);
            insured.setDocument(savedDocument);

            // Retrieve the existing AutoInsurance associated with the insured
            AutoInsurance existingAutoInsurance = insured.getAutoInsurance();

            // Associate with AutoInsurance if autoInsuranceId is provided
            if (autoInsuranceId != null) {
                AutoInsurance autoInsurance = autoInsuranceRepository.findById(autoInsuranceId).orElse(null);
                if (autoInsurance != null) {
                    if (existingAutoInsurance != null) {
                        // If an existing AutoInsurance is associated with the insured, update its fields
                        existingAutoInsurance.setCollisionDeductible(autoInsurance.getCollisionDeductible());
                        existingAutoInsurance.setUninsuredMotoristDeductible(autoInsurance.getUninsuredMotoristDeductible());
                        existingAutoInsurance.setAutoPlan(autoInsurance.getAutoPlan());
                        existingAutoInsurance.setTotalPrice(autoInsurance.getTotalPrice());
                        // Set any other fields that need to be updated
                    } else {
                        // If there was no existing AutoInsurance, associate the new AutoInsurance with the insured
                        insured.setAutoInsurance(autoInsurance);
                    }
                }
            }
            
            // Retrieve the existing Vehicle associated with the insured
            Vehicle existingVehicle = insured.getVehicle();

            if (vehicleId != null) {
                Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
                if (existingVehicle != null) {
                    // If an existing Vehicle is associated with the insured, update its fields
                    existingVehicle.setMake(vehicle.getMake());
                    existingVehicle.setModel(vehicle.getModel());
                    existingVehicle.setVin(vehicle.getVin());
                    existingVehicle.setYear(vehicle.getYear());
                    // Set any other fields that need to be updated
                } else {
                    // If there was no existing Vehicle, associate the new Vehicle with the insured
                    insured.setVehicle(vehicle);
                }
            }
            
            insured.setStatus("pending");
            // Save the modified insured object (including associated entities)
            Insured savedInsured = insuredService.saveInsured(insured);

            return new ResponseEntity<>(savedInsured, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Insured>> getAllInsured() {
        List<Insured> insuredList = insuredService.getAllInsured();
        return new ResponseEntity<>(insuredList, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Insured> getInsuredByEmail(@PathVariable String email) {
        Insured insured = insuredService.getInsuredByEmail(email);

        if (insured != null) {
            return ResponseEntity.ok(insured);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Insured> getInsuredByPhone(@PathVariable String phone) {
        Insured insured = insuredService.getInsuredByPhone(phone);
        if (insured != null) {
            return ResponseEntity.ok(insured);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
