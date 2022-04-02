package com.careerdevs.NewResources.Models;


import com.fasterxml.jackson.annotation.JsonProperty;

/*
    "id": 1456,
    "post_id": 1419,
    "name": "Bela Tandon",
    "email": "tandon_bela@ward-harvey.io",
    "body": "Asperiores quo recusandae. Asperiores omnis voluptatibus. Ut molestiae quos. Quis praesentium qui."
  }*/
public class CommentModel {

    private int id;

  @JsonProperty("post_id")
    private int postId;


    private String name;

    private String email;


    private String body;


    public CommentModel(){

    }

    public CommentModel(int id, int postId,String name, String email,String body){
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;

    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
