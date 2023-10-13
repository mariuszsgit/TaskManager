package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "appFile/tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {

        String[][] tasks;
        tasks = getTasks(FILE_NAME);


        // drawMenuInterface();
        /* Scanner scanner = new Scanner(System.in);

        while (true) {
            String getMenuPosition = scanner.nextLine();
            System.out.println(getMenuPosition);

            switch (getMenuPosition) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "list":
                    listTasks();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
        }*/

    }

    /*
     * Draw the Menu interface */
    public static void addTask() {
        System.out.println("add\nPlease add task description");
    }

    public static void removeTask() {
        System.out.println("remove\nPlease select number to remove:");

    }

    public static void listTasks() {
        System.out.println("list\nList of available tasks:");
    }

    public static String[][] getTasks(String fileName) {
        String[][] tasksArray = null;

        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            System.out.println("tasks.cvs file missing");
        } else {

            try {
                for (String line : Files.readAllLines(filePath)) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return tasksArray;
    }

    public static void drawMenuInterface() {
        System.out.println(ConsoleColors.BLUE + "Available options:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}