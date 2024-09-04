package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String [] actions = input.split(" ");
        while(!"exit".equals(input)) {

            switch (actions[0]){
                case "add":
                    String description = input.split(" ", 2)[1];

                    TaskManager taskM = new TaskManager();
                    Task task = taskM.addTask(description);
                    System.out.println("Task added successfully (ID:" + task.getId() + ")");
                    input = scanner.nextLine();
                    break;
                case "delate":
                    System.out.println("delate task");
                    input = scanner.nextLine();
                    break;
                case "update":
                    System.out.println("update task");
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