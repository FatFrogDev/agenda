package controller;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping(path = {"/", "/index"})
    public String index(){
        return "index";
    }
}
