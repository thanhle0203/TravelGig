package com.thanhle.controller;

import com.thanhle.domain.Claim;
import com.thanhle.domain.ClaimImage;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.VehicleRepository;
import com.thanhle.service.ClaimService;
import com.thanhle.service.VehicleService;

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
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    public ClaimController(ClaimService claimService, VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.claimService = claimService;
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }
    
    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestParam("image") MultipartFile[] images,
                                             @RequestParam("accidentDate") LocalDate accidentDate,
                                             @RequestParam("description") String description,
                                             @RequestParam("vehicleMake") String vehicleMake,
                                             @RequestParam("vehicleModel") String vehicleModel,
                                             @RequestParam("vehicleYear") int vehicleYear) {

        // Process the image files here and save them to the desired location
        List<ClaimImage> imageUrls = saveImages(images);

        // Create the claim object
        Claim claim = new Claim();
        claim.setAccidentDate(accidentDate);
        claim.setDescription(description);
        claim.setStatus("Pending"); // Set the initial status

        // Check if the vehicle already exists in the database
        //Vehicle existingVehicle = vehicleRepository.findByMake(vehicleMake);
        Vehicle existingVehicle = vehicleService.findByMakeAndModel(vehicleMake, vehicleModel);
        Vehicle vehicle;
        if (existingVehicle != null) {
            // Vehicle with the same make and model already exists
            vehicle = existingVehicle;
        }      
        else {
             // Create a new vehicle object
            vehicle = new Vehicle();
            vehicle.setMake(vehicleMake);
            vehicle.setModel(vehicleModel);
            vehicle.setYear(vehicleYear);
            // Save the new vehicle object
            vehicle = vehicleService.saveVehicle(vehicle);
        }
     
        //vehicle = vehicleService.saveVehicle(vehicle);
        claim.setVehicle(vehicle);

        //claim.setVehicle(vehicle);
        claim.setImages(imageUrls);

        // Save the claim
        
        Claim createdClaim = claimService.createClaim(claim);

        if (createdClaim != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClaim);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }



    // Helper method to save the uploaded images
    private List<ClaimImage> saveImages(MultipartFile[] images) {
        List<ClaimImage> imageUrls = new ArrayList<>();
        String uploadDir = "src/main/resources/static/images/claims/"; // Specify the desired location to save the images

        // Create the upload directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Process each image file
        for (MultipartFile image : images) {
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            try {
                Path filePath = Path.of(uploadDir, filename);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                byte[] imageData = image.getBytes(); // Read the image file and convert it to a byte array

                imageUrls.add(new ClaimImage(filename, imageData)); // Create the ClaimImage object with the filename and byte array

            } catch (IOException e) {
                // Handle the exception accordingly
                e.printStackTrace();
            }
        }

        return imageUrls;
    }

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
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
}
