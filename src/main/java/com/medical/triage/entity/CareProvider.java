package com.medical.triage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CareProvider extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String specialization;

    @Column(name = "service_number")
    private String serviceNumber;

    public CareProvider(){}

    public CareProvider(String firstName, String middleName, String lastName, String sex, Date dob, String serviceNumber, String specialization) {
        super(firstName, middleName, lastName, sex, dob );
        this.serviceNumber = serviceNumber;
        this.specialization = specialization;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }
}
