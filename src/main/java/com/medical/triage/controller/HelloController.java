package com.medical.triage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.medical.triage.entity.CareProvider;
import com.medical.triage.entity.ProviderSpecialization;
import com.medical.triage.repository.CareProviderRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CareProviderRepository careProviderRepository;
    @RequestMapping("/symptoms")
    public String welcome() {
        return "Welcome to Triage application";
    }

    @RequestMapping(
            value = "/provider/specialization",
            method = RequestMethod.POST,
            produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            headers = "Accept=application/json")
    public ResponseEntity<Iterable<CareProvider>> getDoctorsMatchingSpecialization(@RequestBody String specializations) {

        System.out.println("Request body: " + specializations);
        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(specializations, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Decoded Request body: " + decodedString);

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
}
