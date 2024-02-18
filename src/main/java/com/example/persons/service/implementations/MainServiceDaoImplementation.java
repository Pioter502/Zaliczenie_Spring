package com.example.persons.service.implementations;

import com.example.persons.model.Person;
import com.example.persons.model.PersonDto;
import com.example.persons.service.MainService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dao")
@AllArgsConstructor
@Component
public class MainServiceDaoImplementation implements MainService {

    private final List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> getAllPerson() {
        return persons;
    }

    @Override
    public Person getPersonById(Integer id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        if (personDto.getSurname() == null || personDto.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Nazwisko nie może być puste");
        }
        if (personDto.getName() == null || personDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Imię nie może być puste");
        }
        int nextId = findMaxId() + 1;
        Person personToAdd = new Person();
        personToAdd.setId(nextId);
        personToAdd.setName(personDto.getName());
        personToAdd.setSurname(personDto.getSurname());
        persons.add(personToAdd);
        return personToAdd;
    }

    private int findMaxId() {
        return persons.stream()
                .mapToInt(Person::getId)
                .max()
                .orElse(0);
    }



    @Override
    public Person updatePerson(Integer id, PersonDto updatedPersonDto) {
        Optional<Person> personToUpdate = persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst();

        personToUpdate.ifPresent(person -> {
            if (updatedPersonDto.getName() != null) {
                person.setName(updatedPersonDto.getName());
            }
            if (updatedPersonDto.getSurname() != null) {
                person.setSurname(updatedPersonDto.getSurname());
            }
        });

        return personToUpdate.orElse(null);
    }

    @Override
    public void deletePerson(Integer id) {
        persons.removeIf(person -> person.getId() == id);
    }
}