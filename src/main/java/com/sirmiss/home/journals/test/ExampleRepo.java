package com.sirmiss.home.journals.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface ExampleRepo extends JpaRepository<ExampleEntity, Integer> {
}
