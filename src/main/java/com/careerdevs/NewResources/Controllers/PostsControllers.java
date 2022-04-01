package com.careerdevs.NewResources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/posts")
public class PostsControllers {

    @Autowired
    private Environment env;




    // getting posts by id using pathVariable
    @GetMapping("/{id}")
    public ResponseEntity postsOfUsers(RestTemplate restTemplate,
                                       @PathVariable("id") int postId) {
        try {
            String url = "https://gorest.co.in/public/v2/posts/"+ postId ;
//            String token = env.getProperty("API_KEY");
//            url += "&access-token=" + token;

            return new ResponseEntity<>(restTemplate.getForObject(url, Object.class),HttpStatus.OK);


        } catch (Exception e) {
            System.out.println(e.getClass());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }

    @DeleteMapping("/{id}")

    public Object deletePost(RestTemplate restTemplate,@PathVariable("id") String id){
        try{
            String url = "https://gorest.co.in/public/v2/posts/"+ id;
            String token = env.getProperty("API_KEY");
            url += "?access-token="+token;

            //Object deletedComment = restTemplate.getForObject(url,Object.class);


            System.out.println(url);

            //using the restTemplate delete method.
            restTemplate.delete(url);
            return "sucessfully deleted the user " + id;

            //  return new ResponseEntity(deletedComment,HttpStatus.OK);


        }catch(Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
