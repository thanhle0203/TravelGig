package com.thanhle.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class AutoInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    @Column(length = 2000)
    private String description;

    private Double maximumCoverage;

    private Double minimumPremium;

    @OneToMany(mappedBy = "autoInsurance", cascade = CascadeType.ALL)
    private List<Plan> plans;

    // Constructors, getters and setters...

    public AutoInsurance() {}

    public AutoInsurance(String name, String type, String description, Double maximumCoverage, Double minimumPremium) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.maximumCoverage = maximumCoverage;
        this.minimumPremium = minimumPremium;
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

    public Double getMaximumCoverage() {
        return maximumCoverage;
    }

    public void setMaximumCoverage(Double maximumCoverage) {
        this.maximumCoverage = maximumCoverage;
    }

    public Double getMinimumPremium() {
        return minimumPremium;
    }

    public void setMinimumPremium(Double minimumPremium) {
        this.minimumPremium = minimumPremium;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
