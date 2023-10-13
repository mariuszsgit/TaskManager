package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "appFile/tasks.csv";

    public static void main(String[] args) {

        String[][] tasks;
        tasks = readTasksFromFile(FILE_NAME);
        drawMenuInterface();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Podaj coś: ");
            String getMenuPosition = scanner.nextLine();

            switch (getMenuPosition) {
                case "add":
                    tasks = addTask(tasks);
                    break;
                case "remove":
                    listTasks(tasks);
                    tasks = removeTask(tasks);
                    break;
                case "list":
                    listTasks(tasks);
                    break;
                case "save":
                    writeTasksToFile(tasks, FILE_NAME);
                    System.out.println("Saved!");
                    break;
                case "exit":
                    writeTasksToFile(tasks, FILE_NAME);
                    System.out.println("Exiting!");
                    System.exit(0);
                default:
                    System.out.println("Please select a correct option.");
            }
            drawMenuInterface();
        }
    }

    /*
     * Draw the Menu interface */
    public static String[][] addTask(String[][] tasks) {
        System.out.println(ConsoleColors.GREEN + "add" + ConsoleColors.RESET);
        Scanner scan = new Scanner(System.in);
        boolean readTasks = true;
        while (readTasks) {
            System.out.println();
            System.out.print("Please add task description, 0-ends: ");
            String description = scan.nextLine();
            if (description.equals("0")) break;

            System.out.print("Please add task due date (YYYY-MMM-DD): ");
            String date = scan.nextLine();
            if (date.equals("0")) break;

            System.out.print("This is important task, true/false: ");
            String important = scan.nextLine();
            if (important.equals("0")) break;

            String[] newTask = new String[3];
            newTask[0] = description;
            newTask[1] = date;
            newTask[2] = important;
            tasks = addToDoubleArray(tasks, newTask);
        }
        return tasks;
    }

    public static String[][] removeTask(String[][] taskToRemove) {
        System.out.println(ConsoleColors.GREEN + "remove");
        Scanner scan = new Scanner(System.in);
        System.out.print(ConsoleColors.BLUE + "Please select number to remove: " + ConsoleColors.RESET);
        String input = scan.nextLine();

        while (!(Integer.parseInt(input) >= 0)) {
            System.out.print(ConsoleColors.BLUE + "Please select number to remove: " + ConsoleColors.RESET);
            input = scan.nextLine();
        }
        taskToRemove = ArrayUtils.remove(taskToRemove, Integer.parseInt(input));

        return taskToRemove;
    }

    /*
     * List available tasks
     * */
    public static void listTasks(String[][] tasksToList) {
        System.out.println(ConsoleColors.BLUE + "List of available tasks:" + ConsoleColors.RESET);
        for (int i = 0; i < tasksToList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < tasksToList[i].length; j++) {

                if (j != tasksToList[i].length - 1) {
                    System.out.print(tasksToList[i][j] + " ");
                }
            }
            if (Boolean.parseBoolean(tasksToList[i][2])) {
                System.out.print(" Status:" + ConsoleColors.RED + " Important" + ConsoleColors.RESET);
            } else {
                System.out.print(" Status: Normal");
            }
            System.out.println();
        }
    }

    /*
     * Reads data from file
     */
    public static String[][] readTasksFromFile(String fileName) {
        String[][] tasksArray = new String[0][0];
        Path filePath = Paths.get(fileName);
        try {
            if (!Files.exists(filePath)) {
                System.out.println("tasks.cvs file missing. Creating new file");
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error. Can't create a file.");
            e.printStackTrace();
        }

        try {
            for (String fileLines : Files.readAllLines(filePath)) {
                tasksArray = addToDoubleArray(tasksArray, fileLines.trim().split(", "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksArray;
    }

    /*
     * Writes available tasks to file
     * */
    public static void writeTasksToFile(String[][] tasks, String fileName) {

        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            System.out.println(ConsoleColors.RED + "The tasks.cvs file is missing.");
            System.exit(0);
        }
        List<String> tekstToSave = new ArrayList<>();

        for (String[] task : tasks) {
            String taskLine = task[0] + ", " + task[1] + ", " + task[2];
            tekstToSave.add(taskLine);
        }
        try {
            Files.write(filePath, tekstToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Adds element to an Array
     * */
    public static String[][] addToDoubleArray(String[][] tab, String[] element) {
        String[][] newTab = Arrays.copyOf(tab, tab.length + 1);
        newTab[tab.length] = element;
        return newTab;
    }

    public static void drawMenuInterface() {
        System.out.print(ConsoleColors.BLUE + "Available options: ");
        System.out.println(ConsoleColors.RESET + "add, remove, list, save, exit");
        System.out.print("> ");
    }
}