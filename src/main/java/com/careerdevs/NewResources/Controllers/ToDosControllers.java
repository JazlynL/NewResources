package com.careerdevs.NewResources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/todos")
public class ToDosControllers {


    @Autowired
    private Environment env;


    @GetMapping("/{id}")
    public ResponseEntity todoUsers(RestTemplate restTemplate, @PathVariable ("id") String id){
        try{
            String url = "https://gorest.co.in/public/v2/todos/"+ id;
            return new ResponseEntity<>(restTemplate.getForObject(url,Object.class), HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getClass());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
