package com.springdatajpa.crud.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdatajpa.crud.controller.PersonApiController;
import com.springdatajpa.crud.domain.PersonModel;
import com.springdatajpa.crud.dto.PersonDto;
import com.springdatajpa.crud.mapper.PersonMapper;
import com.springdatajpa.crud.repository.PersonRepository;
import com.springdatajpa.crud.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonApiController.class)
public class PersonApiControllerTests {

    @Autowired
    private MockMvc mockMvc;
    //fix this later cannot autowired
//    @Autowired
//    private PersonMapper personMapper;
    @MockBean
    private PersonService personService;
    @MockBean
    private PersonRepository personRepository;


    @Test
    public void testCreatePerson() throws Exception {
        PersonMapper personMapper = new PersonMapper();

        PersonDto personDto = new PersonDto();
        personDto.setFirstname("Controller");
        personDto.setLastname("Controller");
        personDto.setAge(1);

        PersonModel personModel = personMapper.fromPersonDtoToPersonEntitySave(personDto);

        String URI = "/api/person";

        String inputInJson = this.mapToJson(personModel);
        Mockito.when(personService.savePerson(Mockito.any(PersonDto.class))).thenReturn(personModel);
        //URI from your /api/person
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        //return of json format
        MvcResult result = mockMvc.perform(requestBuilder).andReturn(); //if saved if no value then not save
        MockHttpServletResponse response = result.getResponse(); //get response

        String outputInJson = response.getContentAsString(); // whole content

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        System.out.println("pompom response: " + result.getResponse().getContentAsString());
    }

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void testGetAllPerson() throws Exception {



        PersonModel model = new PersonModel();
        model.setFirstname("Controller");
        model.setLastname("Controller");
        model.setAge(1);

        PersonModel model2 = new PersonModel();
        model2.setFirstname("Controller2");
        model2.setLastname("Controller2");
        model2.setAge(2);

        List<PersonModel> personList = new ArrayList<>();
        personList.add(model);
        personList.add(model2);

        Mockito.when(personService.getPerson()).thenReturn(personList);

        String URI = "/api/person";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("result List \n" + result.getResponse().getContentAsString());

        String expectedJson = this.mapToJson(personList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }
}
