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
    
    private Integer drivingRecord;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Document document;

    public Insured() {
        super();
    }

    public Insured(String name, String email, String dob, String phone, Integer drivingRecord, Address address, Document document) {
        super();
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.drivingRecord = drivingRecord;
        this.address = address;
        this.document = document;
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
    
    public Document getDocuement() {
    	return document;
    }
    
    public void setDocument(Document document) {
    	this.document = document;
    }
}
