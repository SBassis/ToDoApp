package com.lib.todoapp;

public class Task  {

    private String name;
    private String status;
    private String dueDate;
    private String description;
    @Override
    public String toString() {
        return " Task Name: " + name  /* + '\n' +
                "Status: " + status + '\n' +
                "Due Date: " + dueDate + '\n' +
                "Description: " + description*/ ;
    }

    public static final Task[] tasks = {
            new Task("Study", "Due", "11/11/2023", "Ai and Mobile"),
            new Task("Reading Qur'an", "Due", "11/11/2023", "Al-Anfal"),
            new Task("Feed The Cats", "Due", "11/11/2023", "The kittens")
    };


    public Task(String name, String status, String dueDate, String description) {
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
