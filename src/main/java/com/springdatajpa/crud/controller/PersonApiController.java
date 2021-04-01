package com.springdatajpa.crud.controller;

import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.dto.PersonDto;
import com.springdatajpa.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins= { "http://localhost:8081", }, maxAge=3000)
public class PersonApiController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity getPerson(){

//        return personService.getPerson();
        return new ResponseEntity(personService.getPerson(), HttpStatus.OK);
    }
    @GetMapping("/age")
    public ResponseEntity<PersonDto>findTopByAge(){

        return new ResponseEntity(personService.findTopByOrderByAgeDesc(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity <PersonDto> save(@RequestBody PersonDto personDto){
        return new ResponseEntity(personService.savePerson(personDto), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity <PersonDto> update(@RequestBody PersonDto personDto){
        return new ResponseEntity(personService.updatePerson(personDto),HttpStatus.OK);
    }
    /*http://localhost:8081/api/person?id=1*/
    @DeleteMapping
    public ResponseEntity <Integer> delete (@RequestParam ("id") Integer id){
        return new ResponseEntity(personService.deletePerson(id),HttpStatus.OK);
    }
}
