package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "LocalHost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        Tasks tasks;
        System.out.println("Здравствуйте, я Менеджер Задач");
        while (true) {
            System.out.println("Доступны операции: 1. добавить; 2. удалить");
            System.out.println("Выберите номер и через пробел введите название");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            } else {
                try (Socket clientSocket = new Socket(HOST, PORT);
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String[] userEnter = input.split(" ");
                    String numberTask = userEnter[0];
                    String nameTask = userEnter[1];
                    switch (numberTask) {
                        case "1":
                            tasks = new Tasks(Operations.ADD, nameTask);
                            String output1 = gson.toJson(tasks);
                            out.println(output1);
                            break;
                        case "2":
                            tasks = new Tasks(Operations.REMOVE, nameTask);
                            String output2 = gson.toJson(tasks);
                            out.println(output2);
                            break;
                        default:
                            System.out.println("Такой комманды нет");
                            break;
                    }
                    String result = in.readLine();
                    System.out.println(result.replace(" ", "\n"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
