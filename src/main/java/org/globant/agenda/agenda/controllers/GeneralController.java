package org.globant.agenda.agenda.controllers;

import org.globant.agenda.agenda.model.Person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping(path = {"/index"})
    public Person index(){
        return new Person();
    }

    @GetMapping(path = {"/api"})
    public String api(){
        return "api";
    }
}
