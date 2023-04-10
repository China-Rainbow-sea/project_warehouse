package com.example.communityepidemicassistant;

public class Task {
    private String task_title;
    private String task_date;
    private String task_abstract;
    private String task_detail;
    private String task_iffinished;
    private String task_img;

    public Task(String task_title, String task_date, String task_abstract,String task_detail,String task_iffinished,String task_img) {
        this.task_title = task_title;
        this.task_date = task_date;
        this.task_abstract = task_abstract;
        this.task_detail = task_detail;
        this.task_iffinished=task_iffinished;
        this.task_img=task_img;
    }

    public String getTask_title() {
        return task_title;
    }
    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_date() {
        return task_date;
    }
    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    public String getTask_abstract() {
        return task_abstract;
    }
    public void setTask_abstract(String task_abstract) {
        this.task_abstract = task_abstract;
    }

    public String getTask_detail() {
        return task_detail;
    }

    public void setTask_iffinished(String task_iffinished) {
        this.task_iffinished = task_iffinished;
    }

    public String getTask_iffinished() {
        return task_iffinished;
    }

    public void setTask_detail(String task_detail) {
        this.task_detail = task_detail;
    }

    public String toString(){
        return "Task{" +
                "title='" + task_title + '\'' +
                ", date='" + task_date + '\'' +
                ",abstract'"+task_abstract+'\''+
                ",detail'"+task_detail+'\''+
                ",iffinished'"+task_iffinished+'\''+
                ",img'"+task_img+'\''+
                '}';
    }


}
