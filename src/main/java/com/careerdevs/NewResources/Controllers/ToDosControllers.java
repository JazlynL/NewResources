package com.careerdevs.NewResources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/{id}")

    public Object toDoDelete(RestTemplate restTemplate, @PathVariable("id")String id){
        try{
            String url = "https://gorest.co.in/public/v2/todos/"+id;
            String token = env.getProperty("API_KEY");
            url+= "?access-token=" + token;

            restTemplate.delete(url);
            return "Successfully deleted user: " + id;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
