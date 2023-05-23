package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
public class Insured {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String dob;

    private String phone;
    
    @Lob
    private byte[] driverLicense;


    private Integer drivingRecord;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Insured() {
        super();
    }

    public Insured(String name, String email, String dob, String phone, byte[] driverLicense, Integer drivingRecord, Address address) {
        super();
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.driverLicense = driverLicense;
        this.drivingRecord = drivingRecord;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(byte[] driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Integer getDrivingRecord() {
        return drivingRecord;
    }

    public void setDrivingRecord(Integer drivingRecord) {
        this.drivingRecord = drivingRecord;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
