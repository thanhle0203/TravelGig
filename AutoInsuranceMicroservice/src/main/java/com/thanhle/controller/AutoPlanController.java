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
        AutoPlan createdPlan = autoPlanService.savePlan(autoPlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        autoPlanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
