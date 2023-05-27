package com.thanhle.controller;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.service.AutoInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
@RequestMapping("/api/auto-insurances")
public class AutoInsuranceController {

    private final AutoInsuranceService autoInsuranceService;

    @Autowired
    public AutoInsuranceController(AutoInsuranceService autoInsuranceService) {
        this.autoInsuranceService = autoInsuranceService;
    }

    @GetMapping
    public ResponseEntity<List<AutoInsurance>> getAllAutoInsurances() {
        List<AutoInsurance> autoInsurances = autoInsuranceService.getAllAutoInsurances();
        return ResponseEntity.ok(autoInsurances);
    }

    @PostMapping
    public ResponseEntity<AutoInsurance> saveAutoInsurance(@RequestBody AutoInsurance autoInsurance) {
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveAutoInsurance(autoInsurance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAutoInsurance);
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
