package com.careerdevs.NewResources.Controllers;

import com.careerdevs.NewResources.Models.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
            String token = env.getProperty("API_KEY");
            url += "?access-token="+token;
            System.out.println(url);
            return restTemplate.getForObject(url,Object.class);

        }catch(Exception e){
            System.out.println(e.getClass());
            return e.getMessage();
        }
    }

    // will refactor this code later once Fields are created  in the model classes
    @DeleteMapping("/{id}")

    public Object deleteComments( RestTemplate restTemplate, @PathVariable("id") String id){
        try{
            String url = "https://gorest.co.in/public/v2/comments/"+ id;
        String token = env.getProperty("API_KEY");
        url += "?access-token="+token;
        restTemplate.delete(url);
        return "Successfully deleted this user "+ id;
        }catch(Exception e){
            System.out.println(e.getClass());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping

    public ResponseEntity createUserComment(RestTemplate restTemplate,
                                                    @RequestBody CommentModel createdComment){
        try{
            String url =  "https://gorest.co.in/public/v2/comments/";
            String token = env.getProperty("API_KEY");
            url += "?access-token=" +token;

            System.out.println(url);


            HttpEntity<CommentModel> request = new HttpEntity<>(createdComment);

            ResponseEntity<CommentModel> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    CommentModel.class
            );


            System.out.println("Successfully Created the user : \n" + createdComment);


            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);



        }catch(HttpClientErrorException.UnprocessableEntity Exception){
            System.out.println(Exception.getMessage());
            return new ResponseEntity(Exception.getClass(),HttpStatus.NOT_FOUND);

        }catch(HttpClientErrorException.NotFound Exception){
            System.out.println(Exception.getMessage());
            return new ResponseEntity(Exception.getClass(),HttpStatus.NOT_FOUND);

        }
        catch(Exception e){
            System.out.println(e.getMessage());

            return new ResponseEntity(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping

    public ResponseEntity <Object> updateComment(RestTemplate restTemplate, @RequestBody CommentModel updatedComment){

        try{
            String url =  "https://gorest.co.in/public/v2/comments/" + updatedComment.getId();
            String token = env.getProperty("API_KEY");
            url += "?access-token=" +token;


            System.out.println("Successfully updated user info :\n" + updatedComment);

            HttpEntity<CommentModel> request = new HttpEntity<>(updatedComment);

            ResponseEntity<CommentModel> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    CommentModel.class
            );



            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);
        }
        catch(HttpClientErrorException.NotFound Exception){
            System.out.println(Exception.getMessage());
            return new ResponseEntity(Exception.getClass(),HttpStatus.NOT_FOUND);

        }catch(Exception e){

            System.out.println(e.getMessage());

            return new ResponseEntity(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);}


    }

}
