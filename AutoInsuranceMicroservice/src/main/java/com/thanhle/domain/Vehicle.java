package com.thanhle.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vin;
    
    //@Column(name = "make", unique = true)
    private String make;
    
    //@Column(name = "model", unique = true)
    private String model;
    
    private float value;
    private int year;
    

    //@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Claim> claims = new ArrayList<>();

    public Vehicle() {
        super();
    }
    

    public Vehicle(int vin, String make, String model, float value, int year) {
    	this.vin = vin;
        this.make = make;
        this.model = model;
        this.value = value;
        this.year = year;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

   // public List<Claim> getClaims() {
        //return claims;
    //}

    //public void setClaims(List<Claim> claims) {
       // this.claims = claims;
    //}

    //public void addClaim(Claim claim) {
        //claims.add(claim);
        //claim.setVehicle(this);
    //}
    
    public int getVin() {
    	return vin;
    }
    
    public void setVin(int vin) {
    	this.vin = vin;
    }
}
