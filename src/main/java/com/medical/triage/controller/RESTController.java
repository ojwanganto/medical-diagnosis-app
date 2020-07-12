package com.medical.triage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.medical.triage.entity.CareProvider;
import com.medical.triage.entity.PatientVisit;
import com.medical.triage.repository.CareProviderRepository;
import com.medical.triage.repository.PatientRepository;
import com.medical.triage.repository.PatientVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RESTController {

    @Autowired
    CareProviderRepository careProviderRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientVisitRepository patientVisitRepository;

    /**
     * REST end point that returns a list of care providers, doctors in this case
     * whose specialization match diagnosis specializations arising from patient symptoms/complaints
     * @param specializations
     * @return a list of CareProvider
     */
    @RequestMapping(
            value = "/provider/specialization",
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json")
    public ResponseEntity<Iterable<CareProvider>> getDoctorsMatchingSpecialization(@RequestBody String specializations) {

        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(specializations, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * We are sending a json string with the format visitDetails={json details}
         * It is therefore important to decode the string and extract the part with required json object
         */
        ObjectMapper mapper = new ObjectMapper();
        List<String> qryParams = new ArrayList<>();
        ArrayNode ob = null;
        try {
            ob = (ArrayNode) mapper.readTree(decodedString.split("=")[1]);// we need the json part of the string
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (int i =0; i < ob.size(); i++) {
            qryParams.add(ob.get(i).textValue());
        }

        try {
            return new ResponseEntity<Iterable<CareProvider>>(careProviderRepository.findProvidersWithMatchingSpecialization(qryParams), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<CareProvider>>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * REST end point for adding a patient visit.
     * It uses the CrudRepository JPA to handle visit composed from JSON payload
     * @param visitDetails
     * @return status OK, or BAD REQUEST HTTP codes depending on the process outcome
     */
    @RequestMapping(
            value = "/patient/addvisit",
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json")
    public ResponseEntity<String> savePatientVisit(@RequestBody String visitDetails) {

        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(visitDetails, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * We are sending a json string with the format visitDetails={json details}
         * It is therefore important to decode the string and extract the part with required json object
         */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode ob = null;
        try {
            ob = (ObjectNode) mapper.readTree(decodedString.split("=")[1]);// we need the json part of the string
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate = null;
        Integer patientId = ob.get("patientId").intValue();
        String doctorId = ob.get("doctorId").textValue();
        String diagnosisList = ob.get("diagnosis").textValue();
        String symptomsList = ob.get("symptoms").textValue();
        String specializationList = ob.get("specialization").textValue();
        String appointmentDateStr = ob.get("appointmentDate").textValue();
        try {
            appointmentDate = df.parse(appointmentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PatientVisit v = new PatientVisit();
        v.setPatient(patientRepository.findById(patientId).get());
        v.setVisitDate(new Date());
        v.setComplaints(symptomsList);
        v.setDiagnosis(diagnosisList);
        v.setDiagnosisSpecialization(specializationList);
        v.setDiagnosisAccuracy(true); // this is a placeholder

        if (doctorId != null && !StringUtils.isEmpty(doctorId)) {
            Integer docId = Integer.parseInt(doctorId);
            v.setAssignedDoctor(careProviderRepository.findById(docId).get());
            v.setAppointmentDate(appointmentDate);
        }


        String result = "{\"result\":\"OK\"}";
        // save visit
        try {
            patientVisitRepository.save(v);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
