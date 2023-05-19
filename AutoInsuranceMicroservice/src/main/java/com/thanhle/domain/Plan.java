package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double basePrice;

    private Integer collisionDeductible;

    private Integer uninsuredMotoristDeductible;

    private Double totalPrice;

    @ManyToOne
    private IAutoInsurance autoInsurance;

    // Constructors, getters and setters...

    public Plan() {}

    public Plan(String name, Double basePrice, Integer collisionDeductible, Integer uninsuredMotoristDeductible) {
        this.name = name;
        this.basePrice = basePrice;
        this.collisionDeductible = collisionDeductible;
        this.uninsuredMotoristDeductible = uninsuredMotoristDeductible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getCollisionDeductible() {
        return collisionDeductible;
    }

    public void setCollisionDeductible(Integer collisionDeductible) {
        this.collisionDeductible = collisionDeductible;
    }

    public Integer getUninsuredMotoristDeductible() {
        return uninsuredMotoristDeductible;
    }

    public void setUninsuredMotoristDeductible(Integer uninsuredMotoristDeductible) {
        this.uninsuredMotoristDeductible = uninsuredMotoristDeductible;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public IAutoInsurance getAutoInsurance() {
        return autoInsurance;
    }

    public void setAutoInsurance(IAutoInsurance autoInsurance) {
        this.autoInsurance = autoInsurance;
    }
}
