package com.thanhle.controller;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;
import com.thanhle.service.AutoInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8282")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auto-insurances")
public class AutoInsuranceController {
    @Autowired
    private AutoInsuranceService autoInsuranceService;

    @GetMapping
    public ResponseEntity<List<AutoInsurance>> getAllAutoInsurances() {
        return new ResponseEntity<>(autoInsuranceService.getAllAutoInsurances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoInsurance> getAutoInsuranceById(@PathVariable Long id) {
        return new ResponseEntity<>(autoInsuranceService.getAutoInsuranceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AutoInsurance> saveAutoInsurance(@RequestBody AutoInsurance autoInsurance) {
        return new ResponseEntity<>(autoInsuranceService.saveAutoInsurance(autoInsurance), HttpStatus.CREATED);
    }
    


    @GetMapping("/selected-plans")
    public ResponseEntity<List<AutoInsurance>> getSelectedPlans() {
        return new ResponseEntity<>(autoInsuranceService.getSelectedPlan(), HttpStatus.OK);
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
        // handle auto insurance confirmation...
        // save or update the autoInsurance object
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveOrUpdate(autoInsurance);
        
        if(savedAutoInsurance == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else{
            return ResponseEntity.ok(savedAutoInsurance);
        }
    }
    
    @PostMapping("/autoConfirmationPlan")
    public ResponseEntity<AutoInsurance> autoConfirmationPlan(@RequestBody AutoInsurance autoInsurance) {
        // validate the autoInsurance data if needed
        if(autoInsurance == null || autoInsurance.getAutoPlan() == null) {
            return ResponseEntity.badRequest().build(); // return HTTP 400 if the request data is invalid
        }

        // save or update the autoInsurance in the database
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveOrUpdate(autoInsurance);

        // check if the autoInsurance was successfully saved or updated
        if(savedAutoInsurance == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // return HTTP 500 if there was a server error
        }

        // return the saved autoInsurance and HTTP 200 to indicate success
        return ResponseEntity.ok(savedAutoInsurance);
    }
    

    
    @PostMapping("/saveSelectedPlan")
    public ResponseEntity<AutoInsurance> saveSelectedPlan(@RequestBody AutoInsurance autoInsurance) {
        AutoInsurance savedInsurance = autoInsuranceService.saveSelectedPlan(autoInsurance);
        if (savedInsurance != null) {
            return ResponseEntity.ok().body(savedInsurance);
        } else {
            return ResponseEntity.badRequest().body(savedInsurance);
        }
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoInsurance(@PathVariable Long id) {
        autoInsuranceService.deleteAutoInsurance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
