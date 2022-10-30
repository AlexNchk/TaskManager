package ru.netology.javacore;

public class Tasks {
    private Operations choice;
    private String task;

    public Tasks(Operations choice, String task) {
        this.choice = choice;
        this.task = task;
    }

    public Operations getChoice() {
        return choice;
    }

    public String getTask() {
        return task;
    }
}
