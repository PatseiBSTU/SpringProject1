package by.patsei.springproject1.controller;

import by.patsei.springproject1.aop.LogAnnotation;
import by.patsei.springproject1.dto.NewPersonDto;
import by.patsei.springproject1.dto.PersonDto;
import by.patsei.springproject1.entity.Person;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.exceptions.ResourceNotFoundException;
import by.patsei.springproject1.service.PersonService;
import by.patsei.springproject1.service.PersonServiceImpl;
import by.patsei.springproject1.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
class PersonController {


    private  final PersonService personService;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
      //  this.personRepository = personRepository;
    }


        @GetMapping(value = {"/swagger2"})
        public String swaggerUi() {
            return "redirect:/swagger-ui.html";
        }
    //, produces = { "application/json" , "application/xml"}
    @GetMapping(value = {"/personList"})
    public List<PersonDto> personList() {
        return Mapper.mapAll(personService.getAllPerson(), PersonDto.class);
    }

    @GetMapping(value = {"/personList/{id}"})
    public PersonDto findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return Mapper.map(personService.getById(id), PersonDto.class);
    }


    @PutMapping(value = "/editPerson/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editPerson(@PathVariable("id") Long id, @Valid @RequestBody PersonDto persondto) throws ResourceNotFoundException {
        personService.getById(id);

        personService.editPerson(Mapper.map(persondto, Person.class),id);

    }

    @PostMapping("/addPerson")
    @ResponseStatus(HttpStatus.CREATED)
    public void  savePerson( @Valid @RequestBody NewPersonDto personDto) {
        personService.addNewPerson(Mapper.map(personDto, Person.class));
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id) throws ResourceNotFoundException {
         personService.deletePerson(personService.getById(id));
    }
}

