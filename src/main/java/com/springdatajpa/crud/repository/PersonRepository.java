package com.springdatajpa.crud.repository;

import com.springdatajpa.crud.domain.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Integer> {
    List<PersonModel> findAllByOrderByAgeDesc();
    List<PersonModel> findByFirstnameAndLastname(String firstname,String lastname);
}
