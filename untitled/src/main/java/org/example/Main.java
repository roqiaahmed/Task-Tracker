package org.example;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while(!"exit".equals(input)) {
            int taskId;
            String [] actions = input.split(" ");
            TaskManager taskM = new TaskManager();
            switch (actions[0]){
                case "add":
                    String description = input.split(" ", 2)[1];
                    Task task = taskM.addTask(description);
                    System.out.println("Task added successfully (ID:" + task.getId() + ")");
                    input = scanner.nextLine();
                    break;
                case "delate":
                    taskId = Integer.parseInt(input.split(" ", 2)[1]);
                    taskM.deleteTask(taskId);
                    System.out.println("deleted");
                    input = scanner.nextLine();
                    break;
                case "update":
                    taskId = Integer.parseInt(input.split(" ", 3)[1]);
                    System.out.println("taskId"+taskId);
                    String newDescription = input.split(" ", 3)[2];
                    System.out.println("des "+ newDescription);
                    taskM.updateTask(taskId, newDescription);
                    input = scanner.nextLine();
                    break;
                case "list":
                    List<Task> tasks = taskM.listTasks(input);
                    System.out.println(tasks);
                    input = scanner.nextLine();
                    break;
                case "mark-in-progress":
                    taskId = Integer.parseInt(input.split(" ", 2)[1]);
                    taskM.updateTaskState(taskId,"in_progress");
                    input = scanner.nextLine();
                    break;
                case "mark-done":
                    taskId = Integer.parseInt(input.split(" ", 2)[1]);
                    taskM.updateTaskState(taskId,"done");
                    input = scanner.nextLine();
                    break;
                default:
                    input = scanner.nextLine();
            }

        }
        scanner.close();
        System.out.println("closed");
    }
}