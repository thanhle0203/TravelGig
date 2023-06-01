package com.thanhle.controller;

import com.thanhle.domain.Vehicle;
import com.thanhle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/makes")
    public ResponseEntity<List<String>> getAllMakes() {
        List<String> makes = vehicleService.findAllMakes();
        return ResponseEntity.ok(makes);
    }

    /*
    @GetMapping("/models")
    public ResponseEntity<List<String>> getModelsByMake(@RequestParam("make") String make) {
        List<String> models = vehicleService.findModelsByMake(make);
        return ResponseEntity.ok(models);
    }
    */
    
    @GetMapping("/models")
    public ResponseEntity<List<String>> getAllModels() {
        List<String> models = vehicleService.findAllModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/years")
    public ResponseEntity<List<Integer>> getAllYears() {
        List<Integer> years = vehicleService.findAllYears();
        return ResponseEntity.ok(years);
    }

    // Implement other endpoints as needed
}
