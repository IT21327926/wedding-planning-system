package com.wedding.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vendors")
public class Vendor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vendor type is required")
    private String vendorType;

    private String description;

    @Min(value = 0, message = "Price must be a positive number")
    private double price;

    public Vendor() {}

    public Vendor(String name, String email, String phone, String vendorType, String description, double price) {
        super(name, email, phone);
        this.vendorType = vendorType;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getRole() {
        return "Vendor";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVendorType() { return vendorType; }
    public void setVendorType(String vendorType) { this.vendorType = vendorType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}