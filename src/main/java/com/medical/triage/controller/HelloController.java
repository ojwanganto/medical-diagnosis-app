package com.medical.triage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/symptoms")
    public String welcome() {
        return "Welcome to Triage application";
    }
}
