package com.thanhle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.thanhle.service.AutoPlanService;
import com.thanhle.domain.AutoPlan;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auto-plans")
public class AutoPlanController {

    @Autowired
    private AutoPlanService autoPlanService;

    @GetMapping
    public ResponseEntity<List<AutoPlan>> getAllPlans() {
        return ResponseEntity.ok(autoPlanService.getAllPlans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoPlan> getPlanById(@PathVariable Long id) {
        AutoPlan autoPlan = autoPlanService.getPlanById(id);
        if (autoPlan != null) {
            return ResponseEntity.ok(autoPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AutoPlan> savePlan(@RequestBody AutoPlan autoPlan) {
    	if (autoPlan.getName().equals("Comprehensive Plan")) {
            autoPlan.setType("Full Coverage");
            autoPlan.setDescription("Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.");
            autoPlan.setBasePrice(2000);
        } else if (autoPlan.getName().equals("Plus Plan")) {
            autoPlan.setType("Partial Coverage");
            autoPlan.setDescription("Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.");
            autoPlan.setBasePrice(1500);
        } else if (autoPlan.getName().equals("Basic Plan")) {
            autoPlan.setType("Basic Coverage");
            autoPlan.setDescription("Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.");
            autoPlan.setBasePrice(1000);
        }

        AutoPlan createdPlan = autoPlanService.savePlan(autoPlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        autoPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
