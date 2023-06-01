package com.thanhle.controller;

import com.thanhle.domain.Claim;
import com.thanhle.domain.ClaimImage;
import com.thanhle.domain.Vehicle;
import com.thanhle.repository.ClaimRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api/claims")
public class ClaimController {

	private final ClaimRepository claimRepository;
    private final ClaimService claimService;
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    public ClaimController(ClaimRepository claimRepository, ClaimService claimService, VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.claimRepository = claimRepository;
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

        // Check if the claim with the same description already exists
        //Claim existingClaim = claimService.findByDescription(description);
        //if (existingClaim != null) {
            // Claim with the same description already exists, return an error response
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        //}

        // Check if the vehicle with the same make, model, and year already exists
        //Vehicle existingVehicle = vehicleService.findByMakeAndModelAndYear(vehicleMake, vehicleModel, vehicleYear);
        //if (existingVehicle == null) {
            // Vehicle does not exist, create a new vehicle object
            Vehicle newVehicle = new Vehicle();
            newVehicle.setMake(vehicleMake);
            newVehicle.setModel(vehicleModel);
            newVehicle.setYear(vehicleYear);
            newVehicle = vehicleService.saveVehicle(newVehicle);
            //existingVehicle = vehicleService.saveVehicle(newVehicle);
        //}

        // Process the image files here and save them to the desired location
        List<ClaimImage> imageUrls = saveImages(images);

        // Create the claim object
        Claim claim = new Claim();
        claim.setAccidentDate(accidentDate);
        claim.setDescription(description);
        claim.setStatus("pending"); // Set the initial status
        claim.setVehicle(newVehicle);
        //claim.setVehicle(existingVehicle);
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
    /*
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
	*/
    
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
            // Check if the image already exists in the list based on filename or content
            boolean isDuplicate = imageUrls.stream().anyMatch(existingImage ->
                    {
						try {
							return existingImage.getFilename().equals(image.getOriginalFilename())
							        || Arrays.equals(existingImage.getData(), image.getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
					});

            if (isDuplicate) {
                // Handle the duplicate image case (e.g., return an error response or skip saving the duplicate)
                continue;
            }

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
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
    
    @GetMapping("/getStatus/{status}")
    public List<Claim> getAllClaims(@PathVariable String status) {
        return claimRepository.findByStatus(status);
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