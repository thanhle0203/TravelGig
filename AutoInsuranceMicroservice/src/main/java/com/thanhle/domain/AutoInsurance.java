package com.thanhle.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class AutoInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int collisionDeductible;
    private int uninsuredMotoristDeductible;
    private double totalPrice;
    private Boolean selected; // Add this field

    @OneToOne
    @JoinColumn(name = "plan_id")
    private AutoPlan autoPlan;

    // constructors
    public AutoInsurance() {
    }

    public AutoInsurance(int collisionDeductible, int uninsuredMotoristDeductible, AutoPlan autoPlan, double totalPrice, Boolean selected) {
        this.collisionDeductible = collisionDeductible;
        this.uninsuredMotoristDeductible = uninsuredMotoristDeductible;
        this.autoPlan = autoPlan;
        this.totalPrice = totalPrice;
        this.selected = selected;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCollisionDeductible() {
        return collisionDeductible;
    }

    public void setCollisionDeductible(int collisionDeductible) {
        this.collisionDeductible = collisionDeductible;
    }

    public int getUninsuredMotoristDeductible() {
        return uninsuredMotoristDeductible;
    }

    public void setUninsuredMotoristDeductible(int uninsuredMotoristDeductible) {
        this.uninsuredMotoristDeductible = uninsuredMotoristDeductible;
    }

    public AutoPlan getAutoPlan() {
        return autoPlan;
    }

    public void setAutoPlan(AutoPlan autoPlan) {
		this.autoPlan = autoPlan;
		
	}

    public double getTotalPrice() {
    	return totalPrice;
    }
    
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;	
	}
	
	public Boolean getSelected() {
        return this.selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

	
}
