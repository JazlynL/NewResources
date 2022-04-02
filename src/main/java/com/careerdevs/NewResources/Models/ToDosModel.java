package com.careerdevs.NewResources.Models;


import com.fasterxml.jackson.annotation.JsonProperty;

/*{
    "id": 1837,
    "user_id": 3667,
    "title": "Aut vinum urbanus cuppedia vorago aequitas sponte ocer delibero cognatus.",
    "due_on": "2022-04-25T00:00:00.000+05:30",
    "status": "completed"
  },*/
public class ToDosModel {

    private int id;

    @JsonProperty("user_id")
    private int userId;

    private String title;

    @JsonProperty("due_on")
    private String dueOn;

    @JsonProperty("status")
    private  String status;

    public ToDosModel(){

    }
    public ToDosModel(int id, int userId,String title,String dueOn,String status){
        this.id = id;
        this.userId = userId;
        this.title =title;
        this.dueOn=dueOn;
        this.status=status;

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDueOn() {
        return dueOn;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ToDosModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", dueOn='" + dueOn + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
