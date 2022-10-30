package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int PORT;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.PORT = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Успешно стартовал - порт: " + PORT);
        Gson gson = new Gson();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String userChoice = in.readLine();
                    Tasks userTask = gson.fromJson(userChoice, Tasks.class);
                    switch (userTask.getChoice()) {
                        case ADD:
                            todos.addTask(userTask.getTask());
                            break;
                        case REMOVE:
                            todos.removeTask(userTask.getTask());
                            break;
                        default:
                            System.out.println("Такой операции нет " + userTask.getChoice());
                    }
                    String answer = todos.getAllTasks();
                    out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер не запущен");
            e.printStackTrace();
        }
    }
}
