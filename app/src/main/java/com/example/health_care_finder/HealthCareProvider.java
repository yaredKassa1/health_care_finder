package com.example.health_care_finder;

public class HealthCareProvider {
    private int id;
    private String name;
    private String type; // e.g., hospital, clinic, pharmacy
    private String address;
    private String phone;
    private String services;

    public HealthCareProvider (int id, String name, String type, String address, String phone, String services) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.services = services;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getServices() { return services; }
}
