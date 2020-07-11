package com.medical.triage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
