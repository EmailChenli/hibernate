package com.example.demo3.controller;


import com.example.demo3.entity.Person;
import com.example.demo3.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @RequestMapping("addPayment")
    public int addPayment(){
        Person person = new Person();
        person.setPersonId(2L);
        person.setAge(18);
        person.setName("tim");
        personRepository.save(person);
        return 1;
    }
}
