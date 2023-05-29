package com.thanhle.controller;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;
import com.thanhle.service.AutoInsuranceService;
import com.thanhle.service.AutoPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auto-insurances")
public class AutoInsuranceController {

    private final AutoInsuranceService autoInsuranceService;
    private final AutoPlanService autoPlanService;

    @Autowired
    public AutoInsuranceController(AutoInsuranceService autoInsuranceService, AutoPlanService autoPlanService) {
        this.autoInsuranceService = autoInsuranceService;
        this.autoPlanService = autoPlanService;
    }

    @GetMapping
    public ResponseEntity<List<AutoInsurance>> getAllAutoInsurances() {
        List<AutoInsurance> autoInsurances = autoInsuranceService.getAllAutoInsurances();
        return ResponseEntity.ok(autoInsurances);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AutoInsurance> getAutoInsuranceById(@PathVariable Long id) {
        AutoInsurance autoInsurance = autoInsuranceService.getAutoInsuranceById(id);
        if (autoInsurance != null) {
            return ResponseEntity.ok(autoInsurance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PostMapping
    public ResponseEntity<AutoInsurance> saveAutoInsurance(@RequestBody AutoInsurance autoInsurance) {
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveAutoInsurance(autoInsurance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAutoInsurance);
    }
    */
    
    @PostMapping()
    public ResponseEntity<?> createAutoInsurance(@RequestBody AutoInsurance autoInsurance) {
        try {
            // Persist AutoPlan if it doesn't exist
            AutoPlan autoPlan = autoInsurance.getAutoPlan();
            if (autoPlan != null) {
                AutoPlan existingAutoPlan = autoPlanService.findByName(autoPlan.getName());
                if (existingAutoPlan == null) {
                    autoPlan = autoPlanService.savePlan(autoPlan);
                } else {
                    autoPlan = existingAutoPlan;
                }
            }
            
            // Set AutoPlan for AutoInsurance
            autoInsurance.setAutoPlan(autoPlan);
            
            // Save AutoInsurance
            AutoInsurance savedAutoInsurance = autoInsuranceService.saveAutoInsurance(autoInsurance);
            
            return ResponseEntity.ok(savedAutoInsurance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create auto insurance.");
        }
    }

    @GetMapping("/selected-plans")
    public ResponseEntity<List<AutoInsurance>> getSelectedPlans() {
        List<AutoInsurance> selectedPlans = autoInsuranceService.getSelectedPlan();
        return ResponseEntity.ok(selectedPlans);
    }

    @GetMapping("/selected-plans/{planAutoId}")
    public ResponseEntity<AutoInsurance> getSelectedPlanByAutoId(@PathVariable Long planAutoId) {
        AutoInsurance selectedPlan = autoInsuranceService.getSelectedPlanByAutoId(planAutoId);
        if (selectedPlan != null) {
            return ResponseEntity.ok(selectedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/confirm-auto-plan")
    public ResponseEntity<AutoInsurance> confirmAutoPlan(@RequestBody AutoInsurance autoInsurance) {
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveOrUpdate(autoInsurance);
        if (savedAutoInsurance != null) {
            return ResponseEntity.ok(savedAutoInsurance);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/saveSelectedPlan")
    public ResponseEntity<AutoInsurance> saveSelectedPlan(@RequestBody AutoInsurance autoInsurance) {
        AutoInsurance savedInsurance = autoInsuranceService.saveSelectedPlan(autoInsurance);
        if (savedInsurance != null) {
            return ResponseEntity.ok(savedInsurance);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoInsurance(@PathVariable Long id) {
        autoInsuranceService.deleteAutoInsurance(id);
        return ResponseEntity.noContent().build();
    }
}
