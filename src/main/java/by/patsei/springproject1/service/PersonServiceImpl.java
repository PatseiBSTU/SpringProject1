package by.patsei.springproject1.service;


import by.patsei.springproject1.entity.Person;

import by.patsei.springproject1.exceptions.ResourceNotFoundException;
import by.patsei.springproject1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    public  void addNewPerson(Person person){
        personRepository.save(person);

    }
    public  void deletePerson(Person person ){
        personRepository.delete(person);

    }

    public  void editPerson(Person person, Long id){
        person.setId(id);
         personRepository.save(person);
    }
    public Person getById(long id) throws ResourceNotFoundException {
            return personRepository.findAllById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

    }
}
