package com.example.persons.controller;

import com.example.persons.model.Person;
import com.example.persons.model.PersonDto;
import com.example.persons.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = mainService.getAllPerson();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonsById(@PathVariable Integer id) {
        Person person = mainService.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        Person createdPerson = mainService.createPerson(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody PersonDto updatedPersonDto) {
        Person updatedPerson = mainService.updatePerson(id, updatedPersonDto);
        if (updatedPerson != null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        mainService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
