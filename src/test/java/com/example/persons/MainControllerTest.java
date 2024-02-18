package com.example.persons;

import com.example.persons.model.Person;
import com.example.persons.model.PersonDto;
import com.example.persons.repository.MainRepository;
import com.example.persons.service.implementations.MainServiceDbImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MainControllerTest {
    @Mock
    private MainRepository personRepository;

    @InjectMocks
    private MainServiceDbImplementation mainService;

    @Test
    void testCreatePerson() {
        PersonDto personDto = new PersonDto();
        personDto.setName("Jan");
        personDto.setSurname("Kowalski");

        Person personToAdd = new Person();
        personToAdd.setId(1);
        personToAdd.setName(personDto.getName());
        personToAdd.setSurname(personDto.getSurname());

        when(personRepository.save(any(Person.class))).thenReturn(personToAdd);

        Person createdPerson = mainService.createPerson(personDto);

        verify(personRepository, times(1)).save(any(Person.class));

        assert createdPerson != null;
        assert createdPerson.getId() == 1;
        assert createdPerson.getName().equals("Jan");
        assert createdPerson.getSurname().equals("Kowalski");
    }

    @Test
    void testDeletePerson() {
        Integer personIdToDelete = 1;

        when(personRepository.findById(personIdToDelete)).thenReturn(Optional.of(new Person()));

        mainService.deletePerson(personIdToDelete);

        verify(personRepository, times(1)).deleteById(personIdToDelete);
    }
}
