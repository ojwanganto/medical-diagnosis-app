package com.medical.triage.controller;

import com.medical.triage.entity.Person;
import com.medical.triage.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/person/")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "register")
    public String addPerson(Person person) {
        return "add-person";
    }

    @PostMapping(value = "add")
    public String savePerson(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-person";
        }
        System.out.println("It is getting in the save code block");
        personRepository.save(person);
        return "index";

    }

    @GetMapping("list")
    public String listPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "list-person";

    }
}

