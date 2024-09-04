package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        if (tasksFile.exists() && tasksFile.length() != 0) {
            try {
                System.out.println(" Tasks before are ===>" + allTasks);
                allTasks = objectMapper.readValue(tasksFile, new TypeReference<List<Task>>(){});
                System.out.println(" Tasks after are ===>" + allTasks);
            } catch (IOException e) {
                e.printStackTrace();
//                allTasks = new ArrayList<>();
            }
        } else {
            System.out.println("File not found or is empty, initializing empty task list.");
            allTasks = new ArrayList<>();
        }
    }

    public void saveTask(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(tasksFile, allTasks);

            System.out.println("Tasks saved successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Task addTask(String description) throws Exception{
        readFile();
//        System.out.println("readFile");
        Task newTask = new Task(description);
//        System.out.println("create task");
        allTasks.add(newTask);
//        System.out.println("add to allTasks");
//        System.out.println(allTasks);
        saveTask();
//        System.out.println("save in file");
        return newTask;
    }
}
