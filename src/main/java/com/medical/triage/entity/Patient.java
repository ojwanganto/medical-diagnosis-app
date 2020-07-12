package com.medical.triage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * An extension of the Person model
 * Holds details about a patient
 */
@Entity
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Records internal/external patient numbers
     * TODO: should be a one-to-many relationship for better flexibility.
     * TODO: should be modeled together as an identifier to allow for proper deduplication in a centralized setup
     */
    @Column(name = "patient_number")
    private String patientNumber;

    public Patient(){

    }
    public Patient(String firstName, String middleName, String lastName, String sex, Date dob, String patientNumber) {
        super(firstName, middleName, lastName, sex, dob);
        this.patientNumber = patientNumber;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

}
