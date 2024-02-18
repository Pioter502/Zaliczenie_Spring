package com.example.persons.service;

import com.example.persons.model.Person;
import com.example.persons.model.PersonDto;

import java.util.List;

public interface MainService {
    List<Person> getAllPerson();
    Person getPersonById(Integer id);
    Person createPerson(PersonDto person);
    Person updatePerson(Integer id, PersonDto person);
    void deletePerson(Integer id);
}
