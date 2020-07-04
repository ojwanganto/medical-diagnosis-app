package com.medical.triage.repository;

import com.medical.triage.entity.CareProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareProviderRepository extends CrudRepository<CareProvider, Integer> {
}
