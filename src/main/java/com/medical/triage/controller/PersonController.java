package com.medical.triage.controller;

import com.medical.triage.entity.Person;
import com.medical.triage.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller for person related activities
 *
 */
@Controller
@RequestMapping(value = "/person/")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    /**
     * Provides a page for registering a person
     * @param person
     * @return
     */
    @GetMapping(value = "register")
    public String addPerson(Person person) {
        return "add-person";
    }

    /**
     * Handles data from person registration page
     * @param person
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "add")
    public String savePerson(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-person";
        }
        personRepository.save(person);
        return "redirect:list";
    }

    /**
     * Provides a page that shows a list of registered persons
     * @param model
     * @return
     */
    @GetMapping("list")
    public String listPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "list-person";

    }

    /**
     * Handles date conversions
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}

