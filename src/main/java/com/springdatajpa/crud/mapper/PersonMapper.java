package com.springdatajpa.crud.mapper;

import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {


//            public  PersonMapper fromPersonEntityToPersonDto(PersonDto personDto){
//
//        return new PersonDto()
//                .set
//    }
    public PersonModel fromPersonDtoToPersonEntitySave(PersonDto personDto) {

        PersonModel personModel = new PersonModel();
        personModel.setFirstname(personDto.getFirstname());
        personModel.setLastname(personDto.getLastname());
        personModel.setAge(personDto.getAge());

        return personModel;
    }
    public PersonModel fromPersonDtoToPersonEntityUpdate(PersonDto personDto) {

        PersonModel personModel = new PersonModel();
        personModel.setId(personDto.getId());
        personModel.setFirstname(personDto.getFirstname());
        personModel.setLastname(personDto.getLastname());
        personModel.setAge(personDto.getAge());

        return personModel;
    }
}
