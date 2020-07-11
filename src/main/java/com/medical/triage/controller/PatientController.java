package com.medical.triage.controller;

import com.medical.triage.entity.Patient;
import com.medical.triage.entity.Person;
import com.medical.triage.repository.PatientRepository;
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

@Controller
@RequestMapping(value = "/patient/")
public class PatientController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PatientRepository patientRepository;

    @GetMapping(value = "register")
    public String addPerson(Patient patient) {
        return "add-patient";
    }

    @PostMapping(value = "add")
    public String savePerson(@Valid Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-patient";
        }
        patientRepository.save(patient);
        return "redirect:list";

    }

    @GetMapping("list")
    public String listPersons(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "list-patient";

    }

    @GetMapping("triage/{id}")
    public String triagePatient(@PathVariable("id") Integer id, Model model) {
        Person person = patientRepository.findById(id).get();
        Date dob = person.getDob();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dobString = df.format(dob);
        String yob = dobString.split("-")[0];
        Integer yobInt = Integer.parseInt(yob);
        model.addAttribute("sex", person.getSex());
        model.addAttribute("yob", yobInt);
        return "triage";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}

