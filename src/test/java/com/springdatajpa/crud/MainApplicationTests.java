package com.springdatajpa.crud;

import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.repository.PersonRepository;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainApplicationTests {


    @Autowired
    private PersonRepository personRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    @Rollback(false)
//    public void savePerson(){
//        PersonModel model = new PersonModel();
//        model.setFirstname("test");
//        model.setLastname("test2");
//        model.setAge(26);
//
//        personRepository.save(model);
//
//
//    }
   /* @Test
    @Order(2)
    public List<PersonModel> listPerson(){

        return personRepository.findAllByOrderByAgeDesc();
    }
*/
}
