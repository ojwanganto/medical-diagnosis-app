package com.medical.triage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * A model for handling a patient visit.
 * A care provider at triage collects complaints, sends to remote diagnosis api, and books an appointment with specialized doctor
 *
 * In this prototype, this model stores all details about a patient encounter with clinical care providers at triage
 * TODO: consider having a separate model for 1. Encounter - with providers, 2. Observation - clinical observations i.e. visit diagnoses
 */
@Entity

public class PatientVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer", name = "patient_id")
    private Patient patient;

    @Column(name = "visit_date")
    private Date visitDate;

    private String complaints;
    private String diagnosis;

    @Column(name = "diagnosis_accuracy")
    private Boolean diagnosisAccuracy;

    @Column(name = "diagnosis_specialization")
    private String diagnosisSpecialization;

    /**
     * We only want to add this if a doctor with matching specialization is found.
     * We therefore make the field nullable
     */
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "integer", name = "provider_id")
    private CareProvider doctor;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "date_created")
    private Date dateCreated;

    //TODO: add more details for audit trail i.e. creator, date changed, change reason, voided (for soft delete)

    public PatientVisit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosisSpecialization() {
        return diagnosisSpecialization;
    }

    public void setDiagnosisSpecialization(String diagnosisSpecialization) {
        this.diagnosisSpecialization = diagnosisSpecialization;
    }

    public CareProvider getDoctor() {
        return doctor;
    }

    public void setAssignedDoctor(CareProvider assignedDoctor) {
        this.doctor = assignedDoctor;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getDiagnosisAccuracy() {
        return diagnosisAccuracy;
    }

    public void setDiagnosisAccuracy(Boolean diagnosisAccuracy) {
        this.diagnosisAccuracy = diagnosisAccuracy;
    }
}
