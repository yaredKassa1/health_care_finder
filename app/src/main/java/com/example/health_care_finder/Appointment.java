package com.example.health_care_finder;

public class Appointment {
    private int id;
    private String patientName;
    private String appointmentDate;
    private String patientDisease;
    private String patientCardNumber;
    private String patientAge;
    private String patientContact;

    public Appointment(int id, String patientName, String appointmentDate, String patientDisease, String patientCardNumber, String patientAge, String patientContact) {
        this.id = id;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.patientDisease = patientDisease;
        this.patientCardNumber = patientCardNumber;
        this.patientAge = patientAge;
        this.patientContact = patientContact;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getPatientDisease() {
        return patientDisease;
    }

    public String getPatientCardNumber() {
        return patientCardNumber;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public String getPatientContact() {
        return patientContact;
    }

    @Override
    public String toString() {
        return patientName + " - " + appointmentDate +
                "\nDisease: " + patientDisease +
                ", Card No: " + patientCardNumber +
                ", Age: " + patientAge +
                ", Contact: " + patientContact; // Customize the display format
    }
}