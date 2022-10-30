package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TodosTests {
    @AfterEach
    public void finalizeTest() {
        System.out.println("Тест завершен");
    }
    @Test
    public void testRemoveTask() {
        Todos todos = new Todos();
        String task1 = "Учеба";
        String task2 = "Чтение";
        String expected = task1;

        todos.addTask(task1);
        todos.addTask(task2);
        todos.removeTask(task2);

        Assertions.assertEquals(expected, todos.getAllTasks());
    }
    @Test
    public void testAddTask(){
        Todos todos = new Todos();
        String task = "Бассейн";
        String expected = "Бассейн";
        todos.addTask(task);
        Assertions.assertEquals(expected, todos.getAllTasks());
    }
    @ParameterizedTest
    @ValueSource(strings = {"Работа", "Боулинг", "Бассейн"})
    public void testAddTaskEquals(String task) {
        Todos todos = new Todos();
        todos.addTask(task);
        String expected = task;
        Assertions.assertEquals(expected, todos.getAllTasks());
    }

}
