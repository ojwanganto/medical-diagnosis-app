package com.medical.triage.controller;

import com.medical.triage.entity.Patient;
import com.medical.triage.entity.PatientVisit;
import com.medical.triage.entity.Person;
import com.medical.triage.repository.PatientRepository;
import com.medical.triage.repository.PatientVisitRepository;
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
import java.util.List;

/**
 * Handles patient related web requests
 */
@Controller
@RequestMapping(value = "/patient/")
public class PatientController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientVisitRepository patientVisitRepository;

    /**
     * Returns a page for registering a new patient
     * @param patient
     * @return
     */
    @GetMapping(value = "register")
    public String addPerson(Patient patient) {
        return "add-patient";
    }

    /**
     * Handles data from patient creation page
     * @param patient
     * @param result
     * @param model
     * @return
     */
    @PostMapping(value = "add")
    public String savePerson(@Valid Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-patient";
        }
        patientRepository.save(patient);
        return "redirect:list";

    }

    /**
     * Returns a page with a list of registered patients
     * @param model
     * @return
     */
    @GetMapping("list")
    public String listPersons(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "list-patient";

    }

    /**
     * Displays the triage page
     * @param id
     * @param model
     * @return
     */
    @GetMapping("triage/{id}")
    public String triagePatient(@PathVariable("id") Integer id, Model model) {
        Patient person = patientRepository.findById(id).get();
        Date dob = person.getDob();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dobString = df.format(dob);
        String yob = dobString.split("-")[0];
        Integer yobInt = Integer.parseInt(yob);
        model.addAttribute("sex", person.getSex());
        model.addAttribute("yob", yobInt);//this is required by apimedic
        model.addAttribute("patient", person);
        return "triage";
    }

    /**
     * Returns visits for a patient
     * @param id
     * @param model
     * @return
     */
    @GetMapping("visits/{id}")
    public String getPatientVisits(@PathVariable("id") Integer id, Model model) {
        Patient patient = patientRepository.findById(id).get();
        List<PatientVisit> patientVisits = patientVisitRepository.findPatientVisitByPatient(patient);
        model.addAttribute("visits", patientVisits);
        model.addAttribute("patient", patient);
        return "list-patient-visit";
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

