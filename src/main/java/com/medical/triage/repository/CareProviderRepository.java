package com.medical.triage.repository;

import com.medical.triage.entity.CareProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareProviderRepository extends CrudRepository<CareProvider, Integer> {
    @Query(" FROM CareProvider WHERE specialization IN (:specializationList)")
    public List<CareProvider> findProvidersWithMatchingSpecialization(@Param("specializationList") List<String> specializationList);
}
