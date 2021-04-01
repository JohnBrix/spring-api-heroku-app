package com.springdatajpa.crud.service;

import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.dto.PersonDto;
import com.springdatajpa.crud.mapper.PersonMapper;
import com.springdatajpa.crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Override
    public List<PersonModel> getPerson() {
        return this.personRepository.findAll();
    }
    @Override
    public PersonModel savePerson(PersonDto personDto) {
        PersonModel personModel = personMapper.fromPersonDtoToPersonEntitySave(personDto);
        return this.personRepository.save(personModel);
    }
    @Override
    public PersonModel updatePerson(PersonDto personDto) {
        PersonModel personModel = personMapper.fromPersonDtoToPersonEntityUpdate(personDto);
        return this.personRepository.save(personModel);
    }

    @Override
    public Integer deletePerson(Integer id) {
        personRepository.deleteById(id);
        return id;
    }
    @Override
    public List<PersonModel> findTopByOrderByAgeDesc() {

        return personRepository.findAllByOrderByAgeDesc();
    }


}
