package com.thanhle.controller;

import com.thanhle.domain.AutoInsurance;
import com.thanhle.domain.AutoPlan;
import com.thanhle.service.AutoInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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


    @PostMapping("/saveSelectedPlan")
    public ResponseEntity<AutoInsurance> saveSelectedPlan(@RequestBody AutoInsurance autoInsurance,
                                                          @RequestParam AutoPlan autoPlan,
                                                          @RequestParam int collisionDeductible,
                                                          @RequestParam int uninsuredMotoristDeductible) {
        AutoInsurance savedAutoInsurance = autoInsuranceService.saveSelectedPlan(autoInsurance, autoPlan, collisionDeductible, uninsuredMotoristDeductible);
        if(savedAutoInsurance == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(savedAutoInsurance);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoInsurance(@PathVariable Long id) {
        autoInsuranceService.deleteAutoInsurance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
