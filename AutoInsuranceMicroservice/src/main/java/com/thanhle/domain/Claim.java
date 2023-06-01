package com.thanhle.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate accidentDate;
    
    //@Column(unique = true)
    private String description;
    
    private String status;
    
    private double repairPrice;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "claim_id")
    private List<ClaimImage> images = new ArrayList<>();

    public Claim() {
        super();
    }

    public Claim(LocalDate accidentDate, String description, String status, Vehicle vehicle, List<ClaimImage> images, double repairPrice) {
        this.accidentDate = accidentDate;
        this.description = description;
        this.status = status;
        this.vehicle = vehicle;
        this.images = images;
        this.repairPrice = repairPrice;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        //vehicle.getClaims().add(this); // Add the claim to the vehicle's claim list
    }


    public List<ClaimImage> getImages() {
        return images;
    }

    public void setImages(List<ClaimImage> images) {
        this.images = images;
    }
    
    public double getRepairPrice() {
    	return repairPrice;
    }
    
    public void setRepairPrice(double repairPrice) {
    	this.repairPrice = repairPrice;
    }
}
