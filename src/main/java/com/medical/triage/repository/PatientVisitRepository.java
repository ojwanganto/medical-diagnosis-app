package com.medical.triage.repository;

import com.medical.triage.entity.PatientVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVisitRepository extends CrudRepository<PatientVisit, Integer> {

}
