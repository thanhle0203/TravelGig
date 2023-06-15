package com.thanhle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.Document;
import com.thanhle.domain.Insured;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.AutoInsuranceRepository;
import com.thanhle.repository.InsuredRepository;
import com.thanhle.repository.VehicleRepository;
import com.thanhle.service.DocumentService;
import com.thanhle.service.InsuredService;
import com.thanhle.service.VehicleService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
//import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

@RestController
@CrossOrigin(origins = "http://localhost:8282", allowCredentials = "true")
@RequestMapping("/api/insured")
public class InsuredController {
	
	private final InsuredRepository insuredRepository;
    private final InsuredService insuredService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;
    private final DocumentService documentService;
    private final AutoInsuranceRepository autoInsuranceRepository;

    @Autowired
    public InsuredController(InsuredRepository insuredRepository, InsuredService insuredService, DocumentService documentService,
                             AutoInsuranceRepository autoInsuranceRepository, VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.insuredRepository = insuredRepository;
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
    
    @PutMapping("{insuredId}/status")
    public ResponseEntity<String> updateClaimStatus(@PathVariable Long insuredId, @RequestBody String status, HttpServletResponse response) {
        insuredService.updateInsuredStatus(insuredId, status);
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Add this line to set the 'Access-Control-Allow-Credentials' header
        return ResponseEntity.ok("Insured status updated successfully.");
    }
    
    @GetMapping("/{insuredId}/generate-pdf")
    public ResponseEntity<byte[]> generateInsurancePdf(@PathVariable Long insuredId) {
        Insured insured = insuredRepository.getById(insuredId);

        if (insured != null && insured.getStatus().equals("approved")) {
            byte[] pdfFile = generatePdf(insured);

            if (pdfFile != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDisposition(ContentDisposition.builder("attachment").filename("insurance.pdf").build());

                return ResponseEntity.ok().headers(headers).body(pdfFile);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    private byte[] generatePdf(Insured insured) {
        // Generate the PDF content for the insured
        // You can use a PDF library like iText or Apache PDFBox to generate the PDF content here

        // Example using iText:
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(new Paragraph("Insured ID: " + insured.getId()));
            document.add(new Paragraph("Name: " + insured.getName()));
            document.add(new Paragraph("Phone: " + insured.getPhone()));
            // Add more insured data to the PDF as needed

            // No need to call document.close() or writer.close()

            return outputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
