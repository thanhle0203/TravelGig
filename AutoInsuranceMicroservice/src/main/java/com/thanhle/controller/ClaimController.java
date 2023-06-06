package com.thanhle.controller;

import com.thanhle.domain.Claim;
import com.thanhle.domain.ClaimImage;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.ClaimImageRepository;
import com.thanhle.repository.ClaimRepository;
import com.thanhle.repository.VehicleRepository;
import com.thanhle.service.ClaimService;
import com.thanhle.service.VehicleService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8282", allowCredentials = "true")
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimRepository claimRepository;
    private final ClaimService claimService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;
    private final ClaimImageRepository claimImageRepository;

    public ClaimController(ClaimRepository claimRepository, ClaimService claimService, VehicleService vehicleService, VehicleRepository vehicleRepository, ClaimImageRepository claimImageRepository) {
        this.claimRepository = claimRepository;
        this.claimService = claimService;
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
        this.claimImageRepository = claimImageRepository;
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestParam("images") MultipartFile[] images,
                                             @RequestParam("accidentDate") LocalDate accidentDate,
                                             @RequestParam("description") String description,
                                             @RequestParam("phone") String phone,
                                             @RequestParam("vehicleMake") String vehicleMake,
                                             @RequestParam("vehicleModel") String vehicleModel,
                                             @RequestParam("vehicleYear") String vehicleYear) {

  
    	 Vehicle newVehicle = new Vehicle();
         newVehicle.setMake(vehicleMake);
         newVehicle.setModel(vehicleModel);
         newVehicle.setYear(vehicleYear);
         newVehicle = vehicleService.saveVehicle(newVehicle);

        List<ClaimImage> claimImages = saveImages(images);

     // Create the claim object
        Claim claim = new Claim();
        claim.setAccidentDate(accidentDate);
        claim.setDescription(description);
        claim.setStatus("pending"); // Set the initial status
        claim.setPhone(phone);
        claim.setVehicle(newVehicle);
        claim.setImages(claimImages);

        Claim createdClaim = claimService.createClaim(claim);

        if (createdClaim != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClaim);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<ClaimImage> saveImages(MultipartFile[] images) {
        List<ClaimImage> claimImages = new ArrayList<>();
        String uploadDir = "src/main/resources/static/images/claims/";

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (MultipartFile image : images) {
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            try {
                Path filePath = Path.of(uploadDir, filename);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                byte[] imageData = image.getBytes();

                ClaimImage claimImage = new ClaimImage();
                claimImage.setFilename(filename);
                claimImage.setData(imageData);

                claimImages.add(claimImageRepository.save(claimImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return claimImages;
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.getClaimById(id);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Claim>> getClaimByPhone(@PathVariable String phone) {
        List<Claim> claim = claimRepository.findByPhone(phone);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{claimId}/status")
    public ResponseEntity<String> updateClaimStatus(@PathVariable Long claimId, @RequestBody String status) {
        claimService.updateClaimStatus(claimId, status);
        return ResponseEntity.ok("Claim status updated successfully.");
    }

    @PutMapping("/{claimId}/repair-price")
    public ResponseEntity<String> updateClaimRepairPrice(@PathVariable Long claimId, @RequestBody double repairPrice) {
        claimService.updateClaimRepairPrice(claimId, repairPrice);
        return ResponseEntity.ok("Repair price updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok("Claim deleted successfully.");
    }
}
