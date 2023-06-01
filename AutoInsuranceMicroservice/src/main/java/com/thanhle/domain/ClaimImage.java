package com.thanhle.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "claim_images")
public class ClaimImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;


    @Lob
    @Column(name = "data")
    private byte[] data;

    // Constructors, getters, and setters

    // Constructors

    public ClaimImage() {
    	super();
    }

    public ClaimImage(String filename, byte[] data) {
        this.filename = filename;
        this.data = data;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

    // Getters and setters

    // ...
}
