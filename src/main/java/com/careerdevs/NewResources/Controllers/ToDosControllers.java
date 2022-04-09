package com.careerdevs.NewResources.Controllers;

import com.careerdevs.NewResources.Models.CommentModel;
import com.careerdevs.NewResources.Models.ToDosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    public ResponseEntity<Object>todoUsers(RestTemplate restTemplate, @PathVariable ("id") String id){
        try{
            String url = "https://gorest.co.in/public/v2/todos/"+ id;
            String token = env.getProperty("API_KEY");
            url+= "?access-token=" + token;
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

    @PostMapping

    public ResponseEntity<Object> createToDO(RestTemplate restTemplate,
                                             @RequestBody ToDosModel createUser){
        try{
            String url = "https://gorest.co.in/public/v2/todos/";
            String token = env.getProperty("API_KEY");
            url+= "?access-token=" + token;
            System.out.println(url);

            HttpEntity<ToDosModel> request = new HttpEntity<>(createUser);

            //another way to do it other than exchange.
//            ToDosModel createdUser = restTemplate.postForObject(url,request,ToDosModel.class);

             ResponseEntity<ToDosModel> response = restTemplate.exchange(
                     url,
                     HttpMethod.POST,
                     request,
                     ToDosModel.class
             );
            System.out.println("Successfully created new user comments: \n" + createUser);
            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }

    @PutMapping

    public ResponseEntity<Object> updateToDO(RestTemplate restTemplate,
                                             @RequestBody ToDosModel updateToDo){
        try{
            String url = "https://gorest.co.in/public/v2/todos/"+ updateToDo.getId();
            String token = env.getProperty("API_KEY");
            url+= "?access-token=" + token;
            System.out.println(url);

            HttpEntity<ToDosModel> request = new HttpEntity<>(updateToDo);

            ResponseEntity<ToDosModel> response = restTemplate.exchange(url,
                    HttpMethod.PUT,
                    request,
                    ToDosModel.class
            );

// using return of response entity to get the body
            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);}

    }
}
