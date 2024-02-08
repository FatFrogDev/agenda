package org.globant.agenda.agenda.controllers;

import org.globant.agenda.agenda.exceptions.ResourceNotFoundException;
import org.globant.agenda.agenda.model.Cellphone;
import org.globant.agenda.agenda.model.Organization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.NonNull;

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
