package com.careerdevs.NewResources.Controllers;

import com.careerdevs.NewResources.Models.PostModel;
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
@RequestMapping("/api/posts")
public class PostsControllers {

    @Autowired
    private Environment env;




    // getting posts by id using pathVariable
    @GetMapping("/{id}")
    public Object postsOfUsers(RestTemplate restTemplate,
                                       @PathVariable("id") int postId) {
        try {
            String url = "https://gorest.co.in/public/v2/posts/"+ postId ;

            String token = env.getProperty("API_KEY");
             url += "?access-token=" + token;



          //refactored code ... Another way
//            var user = restTemplate.getForObject(url, PostModel.class);
//            assert user != null;

            System.out.println(url);
            return new ResponseEntity<>(restTemplate.getForObject(url, PostModel.class),HttpStatus.OK);

            // new return statement with refactored code
//            return user;

        } catch (HttpClientErrorException.NotFound exception) {
            //resource not found  error will output a 404 HTTP status code response
            return new ResponseEntity("user was not found in system ", HttpStatus.NOT_FOUND);
        }catch (Exception e) {
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

    @PostMapping

    public ResponseEntity <Object> postUser(RestTemplate restTemplate,
                                              @RequestBody PostModel postsUser){

        try{
            String url = "https://gorest.co.in/public/v2/posts/";
            String token = env.getProperty("API_KEY");
            url+= "?access-token="+token;
            System.out.println(url);


            HttpEntity<PostModel> request = new HttpEntity<>(postsUser);

            System.out.println("Successfully created new user: " + postsUser );

            //just a note of an alternative way to write the code.
            // PostModel createdPost = restTemplate.postForObject(url, request, PostModel.class);

            ResponseEntity<PostModel> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    PostModel.class
            );

            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);




        }catch (HttpClientErrorException.NotFound exception) {
            //resource not found  error will output a 404 HTTP status code response
            return new ResponseEntity("user was not found in system ", HttpStatus.NOT_FOUND);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping

    public ResponseEntity<Object> finalPut(RestTemplate restTemplate,
                                           @RequestBody PostModel putPost){

        try{

            //remember to use the get ID method in the Post Model class,
            String url = "https://gorest.co.in/public/v2/posts/" +putPost.getId();
            String token = env.getProperty("API_KEY");
            url+= "?access-token="+token;

            //setting up the put request
            HttpEntity<PostModel> request = new HttpEntity<>(putPost);

            System.out.println("Successfully updated user information:\n"+ putPost);
            System.out.println(url);
            // then we will be setting up the response
            ResponseEntity<PostModel> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    PostModel.class
            );

            return new ResponseEntity<>(response.getBody(),HttpStatus.CREATED);


        }catch (HttpClientErrorException.NotFound exception) {
            //resource not found  error will output a 404 HTTP status code response
            return new ResponseEntity("user was not found in system ", HttpStatus.NOT_FOUND);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
