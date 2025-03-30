package com.projects.crud.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
class TesteController {

    @GetMapping("/hello")
    public String testeGet(){

        return "ok";
    }

    @PostMapping("/post")
    public String testePost(@RequestBody String requestBody){

        return requestBody;
    
    }

    @PutMapping("/put")
    public String testePut(@RequestBody String requestBody){

        return requestBody;
    }


}