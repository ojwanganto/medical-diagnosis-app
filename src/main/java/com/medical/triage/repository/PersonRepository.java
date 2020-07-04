package com.medical.triage.repository;

import com.medical.triage.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findPersonByIdentifer(String identifier);
}
