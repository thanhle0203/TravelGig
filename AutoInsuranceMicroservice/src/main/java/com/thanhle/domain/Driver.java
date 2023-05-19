package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private Integer drivingRecord;

    private Integer vehicleValue;

    // Constructors, getters and setters...

    public Driver() {}

    public Driver(String name, Integer age, Integer drivingRecord, Integer vehicleValue) {
        this.name = name;
        this.age = age;
        this.drivingRecord = drivingRecord;
        this.vehicleValue = vehicleValue;
    }

    // Getters and setters...

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDrivingRecord() {
        return drivingRecord;
    }

    public void setDrivingRecord(Integer drivingRecord) {
        this.drivingRecord = drivingRecord;
    }

    public Integer getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(Integer vehicleValue) {
        this.vehicleValue = vehicleValue;
    }
}
