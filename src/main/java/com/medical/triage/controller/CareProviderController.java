package com.medical.triage.controller;

import com.medical.triage.entity.CareProvider;
import com.medical.triage.entity.Patient;
import com.medical.triage.entity.Person;
import com.medical.triage.repository.CareProviderRepository;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/provider/")
public class CareProviderController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CareProviderRepository careProviderRepository;

    @GetMapping(value = "register")
    public String addPerson(CareProvider careProvider) {
        return "add-care-provider";
    }

    @PostMapping(value = "add")
    public String savePerson(@Valid CareProvider provider, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-care-provider";
        }
        careProviderRepository.save(provider);
        return "redirect:list";

    }

    @GetMapping("list")
    public String listProviders(Model model) {
        model.addAttribute("careProviders", careProviderRepository.findAll());
        return "list-care-provider";

    }

    @GetMapping("triage/{id}")
    public String triagePatient(@PathVariable("id") Integer id, Model model) {
        Person person = personRepository.findById(id).get();
        Date dob = person.getDob();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dobString = df.format(dob);
        String yob = dobString.split("-")[0];
        Integer yobInt = Integer.parseInt(yob);
        model.addAttribute("sex", person.getSex());
        model.addAttribute("yob", yobInt);
        return "triage";
    }

    @ModelAttribute(name = "specializations")
    public List<String> specializations() {
        List<String> specializationList = Arrays.asList(
                "Allergology",
                "Anesthesiology",
                "Colon & Rectal Surgery",
                "Dermatology",
                "Emergency Medicine",
                "Family Medicine",
                "Internal medicine",
                "Medical Genetics and Genomics",
                "Neurological Surgery",
                "Nuclear Medicine",
                "Obstetrics & Gynecology Ophthalmology",
                "Orthopaedic Surgery",
                "Otolaryngology",
                "Pathology",
                "Pediatrics",
                "Physical Medicine & Rehabilitation",
                "Plastic Surgery",
                "Preventive medicine",
                "Psychiatry",
                "Radiology",
                "Surgery",
                "Thoracic Surgery",
                "Urology",
                "Cardiology",
                "General practice"
        );
        return specializationList;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}

