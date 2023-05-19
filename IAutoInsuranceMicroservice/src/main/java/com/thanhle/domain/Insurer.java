package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
public class Insurer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insurerId;
    
    private String name;
    
    private String email;
    
    private String dob;
    
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    
    public Insurer() {
    	super();
    }

    public Insurer(Long insurerId, String name, String email, String dob, String phone, Address address) {
    	super();
    	this.insurerId = insurerId;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

    public Long getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(Long id) {
        this.insurerId = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
