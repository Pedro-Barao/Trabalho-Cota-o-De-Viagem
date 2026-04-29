package com.example.aprendendo_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/v1/hello")
public class HelloWorld {
    
    //Forma 1
    @GetMapping("/1")
    public String helloWorld_1() 
    {

        return "Hello World! 1";

    }

    //Forma 2
    @GetMapping("/2")
    public ResponseEntity<String> helloWorld_2() 
    {

        return ResponseEntity.ok("Hello World! 2");

    }

    //Forma 3
    @GetMapping("/3")
    public ResponseEntity<String> helloWorld_3()
    {

        return new ResponseEntity<>("Hello World! 3", HttpStatus.OK);

    }
    
    //Forma 4
    @GetMapping("/4")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld_4() 
    {

        return "Hello World! 4";

    }

}
