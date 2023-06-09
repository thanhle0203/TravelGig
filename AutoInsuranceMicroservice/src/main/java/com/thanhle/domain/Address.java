package com.thanhle.domain;

import jakarta.persistence.*;


@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String street;
    
    private String city;
    
    private String state;
    
    private String zipCode;
    
    private String email;
    
    public Address() {
    	super();
    }
    
    public Address(String street, String city, String state, String zipCode) {
    	super();
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
      
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

}
