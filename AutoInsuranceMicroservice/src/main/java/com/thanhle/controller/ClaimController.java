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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;


@RestController
@CrossOrigin(origins = "http://localhost:8282", allowCredentials = "true")
@RequestMapping("/api/claims")
public class ClaimController {
	private final Logger logger = LoggerFactory.getLogger(ClaimController.class);
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
    public ResponseEntity<Claim> createClaim(@RequestParam("image") MultipartFile[] images,
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
    
    
    /*
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

                // Set the URL for the claim image
                claimImage.setUrl("http://localhost:8282/images/claims/" + filename);

                claimImages.add(claimImageRepository.save(claimImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return claimImages;
    }
	*/

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
    
    @PostMapping("/upload/{claimId}")
    public ResponseEntity<Claim> uploadImages(@PathVariable Long claimId, @RequestParam("image") MultipartFile[] images) {
      Claim claim = claimService.getClaimById(claimId);

      if (claim != null) {
        List<ClaimImage> claimImages = saveImages(images);
        claim.getImages().addAll(claimImages);
        claimService.updateClaim(claim);

        return ResponseEntity.ok(claim);
      } else {
        return ResponseEntity.notFound().build();
      }
    }
    
    
    @GetMapping("/{claimId}/generate-pdf")
    public ResponseEntity<byte[]> generateClaimPdf(@PathVariable Long claimId) {
        Claim claim = claimService.getClaimById(claimId);

        if (claim != null && claim.getStatus().equals("approved")) {
            byte[] pdfFile = generatePdf(claim);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("claim.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfFile);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private byte[] generatePdf(Claim claim) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            document.add(new Paragraph("Claim ID: " + claim.getId()));
            document.add(new Paragraph("Date of Accident: " + claim.getAccidentDate()));
            document.add(new Paragraph("Vehicle VIN: " + claim.getVehicle().getId()));
            document.add(new Paragraph("Vehicle Make: " + claim.getVehicle().getMake()));
            document.add(new Paragraph("Vehicle Model: " + claim.getVehicle().getModel()));
            document.add(new Paragraph("Vehicle Year: " + claim.getVehicle().getYear()));
            document.add(new Paragraph("Phone Number: " + claim.getPhone()));
            document.add(new Paragraph("Repair Price: $" + claim.getRepairPrice()));
        

            document.close();
            writer.close();

            logger.info("PDF generated successfully for claim: {}", claim.getId());

            return outputStream.toByteArray();
        } catch (DocumentException | IOException e) {
            logger.error("Error generating PDF for claim: {}", claim.getId(), e);
            e.printStackTrace();
            return null;
        }
    }

}
