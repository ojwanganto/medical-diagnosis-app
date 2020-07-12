package com.medical.triage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * This model represents all care health care providers who are persons.
 * In this prototype, this model has solely been used to represent a medical doctor
 * TODO: Make the model handle other medical categories
 *
 */
@Entity
public class CareProvider extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * This records a providers area of specialization.
     * TODO: consider a model for specialization and establish a one-to-one relationship (if one can only have just one speciality)
     */
    private String specialization;

    /**
     * This is helpful for legitimacy of a health care provider.
     * Should not be a mandatory variable
     */
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
