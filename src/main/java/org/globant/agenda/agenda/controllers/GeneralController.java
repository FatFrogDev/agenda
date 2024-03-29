package org.globant.agenda.agenda.controllers;

import org.globant.agenda.agenda.model.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @GetMapping(path = {"/index"})
    public Organization index(){
        return new Organization();
    }

    @GetMapping(path = {"/api"})
    public String api(){
        return "api";
    }
}
