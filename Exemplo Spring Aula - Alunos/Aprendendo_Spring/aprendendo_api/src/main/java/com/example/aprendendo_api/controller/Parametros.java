package com.example.aprendendo_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/v1/parametro")
public class Parametros {

    //Endpoint Vazio (Sem /)
    @GetMapping
    public String parametroGet1() 
    {

        return "Hello World!";

    }

    //Endpoint com PathVariable (É obrigattório e muda dependendo do que vier depois do /)
    @GetMapping(value = "/{id}")
    public String parametroGet2(@PathVariable("id") String id) 
    {
        return "Hello World! " + id;

    }
    
    //Endpoint com RequestParam (É opcional e muda dependendo do que vier depois do ?"nome do parametro"="valor do parametro")
    @GetMapping("/nome")
    public String parametroGet3(@RequestParam(value = "name", required = false) String name) 
    {

        return "Hello World! " + name;

    }

}
