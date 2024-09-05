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
                allTasks = objectMapper.readValue(tasksFile, new TypeReference<List<Task>>(){});
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

    public Task getTaskById(int id) throws Exception{
        readFile();
        for(Task task:allTasks){
            int taskId = task.getId();
            if ( taskId == id){
                return task;
            }
        }
            return null;
    }

    public void updateTask(int taskId,String newDescription)throws Exception{
        Task currTask = getTaskById(taskId);

        if(!(currTask == null)){
            currTask.setDescription(newDescription);
            deleteTask(taskId);
            allTasks.add(currTask);
            saveTask();
            System.out.println("updated successfully");
        }else{
        System.out.println("there is no task with this id");
        }
    }

    public void deleteTask(int taskId)throws Exception{
        System.out.println("in deleteTask method " + taskId);
        Task currTask = getTaskById(taskId);
        allTasks.remove(currTask);
        saveTask();
    }

    public List<Task> listTasks(String args) throws Exception{
        String [] inputs = args.split(" ");
        readFile();
        if(inputs.length == 2){
            List<Task> stateTasks = new ArrayList<>();
            String state = inputs[1];
            for(Task task : allTasks){
                if(task.getState().equals(state) ){
                    stateTasks.add(task);
                }
            }
            return stateTasks;
        }
        return allTasks;
    }

    public void updateTaskState(int taskId, String state) throws Exception{
        readFile();
        Task currTask = getTaskById(taskId);
                Status newState = switch (state) {
                    case "done" -> Status.done;
                    case "in_progress" -> Status.in_progress;
                    case "todo" -> Status.todo;
                    default -> null;
                };
        currTask.setState(newState);
        deleteTask(taskId);
        allTasks.add(currTask);
        saveTask();
    }
}
