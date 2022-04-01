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
@RequestMapping("/api/comments")
public class CommentsControllers {

    @Autowired
    private Environment env;


    // getting users comments
    @GetMapping("/{id}")
    public Object usersComments(RestTemplate restTemplate,
                                        @PathVariable("id") int commentsId){
        try{
            String url = "https://gorest.co.in/public/v2/comments/"+ commentsId;
            System.out.println(url);
            return restTemplate.getForObject(url,Object.class);

        }catch(Exception e){
            System.out.println(e.getClass());
            return e.getMessage();
        }
    }

}
