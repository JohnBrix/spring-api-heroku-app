package com.springdatajpa.crud.repository;

import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.repository.PersonRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

//for different testing ng controller
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;
    //asserted ibig sbhn EXPECTED VALUE NA GUSTO MO MANGYAYARI
    //pag rollback false ay pwdi masave ang test data
    @Test
//    @Rollback(false)
//    @Order(1)
    public void testCreatePerson() {
        PersonModel model = new PersonModel("unitTest", "unitTest", 17);
        PersonModel repo = personRepository.save(model);

        assertThat(repo.getId()).isGreaterThan(0);

    }

    //pag rollback false ay pwdi masave ang test data
    //order ay sunod sunod na eexecute by order pag unit test dapat unit test invididuality yn
    @Test
//    @Order(2)
    public void testFindPerson() {
        List <PersonModel> listData = personRepository.findByFirstnameAndLastname("updateTest","updateTest");
        listData.forEach(e->{

            assertThat(e.getFirstname()).isEqualTo("updateTest");
        });

    }

    @Test
//    @Order(3)
    public void testPersonList() {
        List<PersonModel> products = personRepository.findAll();
        assertThat(products).size().isGreaterThan(0);
    }


    @Test
//    @Rollback(false)
//    @Order(4)
    public void testUpdatePerson() {
        PersonModel model = new PersonModel();
        model.setId(1);
        model.setFirstname("updateTest");
        model.setLastname("updateTest");
        model.setAge(69);
        personRepository.save(model);
        assertThat(model.getAge()).isEqualTo(69);
    }
    //failed test to ayusin mo mmya
    @Test
/*    @Rollback(false)*/
    public void testDeletePerson() {
        personRepository.deleteById(1);
        Optional<PersonModel> find = personRepository.findById(1);
        assertThat(find).isEmpty();
    }


}
