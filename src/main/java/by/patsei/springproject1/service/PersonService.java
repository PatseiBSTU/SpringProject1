package by.patsei.springproject1.service;

import by.patsei.springproject1.entity.Person;
import by.patsei.springproject1.exceptions.ResourceNotFoundException;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();
    void addNewPerson(Person person);
    void deletePerson(Person person );
    void editPerson(Person person, Long id);
    Person getById(long id) throws ResourceNotFoundException;


}
