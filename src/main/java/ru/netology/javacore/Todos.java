package ru.netology.javacore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    private List<String> listTask = new ArrayList<>();
    private final int MAX_TASK = 7;

    public void addTask(String task) {
        if (listTask.size() < MAX_TASK) {
            listTask.add(task);
        }
    }

    public void removeTask(String task) {
        if (!listTask.remove(task)) {
            System.out.println("Операция не найдена");
        }
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        String listTasks = listTask.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));
        return listTasks;
    }
}
