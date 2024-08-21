package org.example;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {

    private List<Task> allTasks;
    File tasksFile = new File("tasks.json");

    public TaskManager() {
        allTasks = new ArrayList<>();
    }

    public void readFile() throws Exception {
        System.out.println("befor adding ===>"+ allTasks);
        try (BufferedReader br = new BufferedReader(new FileReader(tasksFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = new Task(line.trim());
                addTask(task);
            }
                System.out.println("after adding ===>"+ allTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addTask(Task newTask) {
        allTasks.add(newTask);
    }
}
