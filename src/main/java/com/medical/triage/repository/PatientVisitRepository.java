package com.medical.triage.repository;

import com.medical.triage.entity.Patient;
import com.medical.triage.entity.PatientVisit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientVisitRepository extends CrudRepository<PatientVisit, Integer> {
    List<PatientVisit> findPatientVisitByPatient(Patient patient);
}
