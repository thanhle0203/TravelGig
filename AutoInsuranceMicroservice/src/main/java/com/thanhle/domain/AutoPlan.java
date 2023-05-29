package com.thanhle.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class AutoPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String description;
    private double basePrice;
    
    //@OneToOne(cascade = CascadeType.ALL)
    //@OneToOne()
   // @JoinColumn(name = "autoInsurance_id")
    //private AutoInsurance autoInsurance;

    // constructors
    public AutoPlan() {
    }
    
    public AutoPlan(String name) {
        this.name = name;
    }


    public AutoPlan(String name, String type, String description, double basePrice) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.basePrice = basePrice;
    }

    // getters and setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    // Factory method to create an AutoPlan instance from a name string
    /*
    public static AutoPlan fromString(String name) {
        if (name.equals("ComprehensivePlan")) {
            return new AutoPlan(name, "Full Coverage", "Comprehensive coverage including collision, liability, uninsured motorist protection, and roadside assistance. Ideal for new, high-value cars.", 2000);
        } else if (name.equals("PlusPlan")) {
            return new AutoPlan(name, "Partial Coverage", "Balanced coverage including collision and liability protection. Suitable for medium-value cars or drivers wanting a balance between cost and coverage.", 1500);
        } else if (name.equals("BasicPlan")) {
            return new AutoPlan(name, "Basic Coverage", "Minimum coverage meeting state requirements, typically including liability coverage only. Suitable for older, low-value cars or drivers needing economical options.", 1000);
        }
        return null;
    }
    */
  
}