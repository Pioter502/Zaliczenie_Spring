package com.example.persons.service.implementations;

import com.example.persons.model.Person;
import com.example.persons.model.PersonDto;
import com.example.persons.repository.MainRepository;
import com.example.persons.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("db")
@AllArgsConstructor
@Component
public class MainServiceDbImplementation implements MainService {

    private MainRepository personRepository;
    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public Person createPerson(PersonDto person) {
        if (person.getSurname() == null || person.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Nazwisko nie może być puste");
        }
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Imię nie może być puste");
        }

        Person personToAdd = new Person();
        personToAdd.setName(person.getName());
        personToAdd.setSurname(person.getSurname());
        return personRepository.save(personToAdd);
    }

    @Override
    public Person updatePerson(Integer id, PersonDto updatedPersonDto) {
        Person personToUpdate = personRepository.findById(id).orElse(null);

        if (personToUpdate != null) {
            if (updatedPersonDto.getName() != null) {
                personToUpdate.setName(updatedPersonDto.getName());
            }
            if (updatedPersonDto.getSurname() != null) {
                personToUpdate.setSurname(updatedPersonDto.getSurname());
            }

            return personRepository.save(personToUpdate);
        }

        return null;
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
