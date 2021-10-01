package by.patsei.springproject1.repository;

import by.patsei.springproject1.entity.Person;
import by.patsei.springproject1.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


    List<Person> findAll();
    Optional<Person> findAllById(Long personaID) ;


}
