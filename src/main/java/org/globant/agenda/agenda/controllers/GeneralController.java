package org.globant.agenda.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class GeneralController {

    @GetMapping(path = {"/index"})
    public String index(){
        return "index";
    }

    @GetMapping(path = {"/api"})
    public String api(){
        return "api";
    }
}
