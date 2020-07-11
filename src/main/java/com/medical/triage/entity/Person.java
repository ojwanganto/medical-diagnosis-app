package com.medical.triage.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Defines properties of a general person
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please provide a first name")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotBlank(message = "Please provide a last name")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String sex;

    @Column(name = "postal_address")
    private String postalAddress;

    @NotBlank(message = "Please provide a county")
    private String county;

    @Column(name = "sub_county")
    private String subCounty;

    private String ward;
    private String email;
    @NotBlank(message = "A person identifier i.e. National ID, Passport no is required")
    private String identifer;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Person() {
    }

    public Person(String firstName, String middleName, String lastName, String sex, Date dob) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.dob = dob;
    }

    public Person(String firstName, String middleName, String lastName, String identifer) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.identifer = identifer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifer() {
        return identifer;
    }

    public void setIdentifer(String identifer) {
        this.identifer = identifer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
