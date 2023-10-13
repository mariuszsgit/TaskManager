package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileNotFoundException;
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
    static final String FILE_NAME_TEMP = "appFile/tasksTemp.csv";

    public static void main(String[] args) {

        String[][] tasks;

        tasks = readTasksFromFile(FILE_NAME);

        //tasks = addTask(tasks);
        tasks = removeTask(tasks);

        writeTasksToFile(tasks, FILE_NAME_TEMP);


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String getMenuPosition = scanner.nextLine();
            System.out.println(getMenuPosition);

            switch (getMenuPosition) {
                case "add":
                    tasks = addTask(tasks);
                    break;
                case "remove":
                    System.out.println(ConsoleColors.GREEN + "remove");
                    tasks = removeTask(tasks);
                    break;
                case "list":
                    System.out.println(ConsoleColors.GREEN + "list\n" +
                            ConsoleColors.BLUE + "List of available tasks:" + ConsoleColors.RESET);
                    listTasks(tasks);
                    break;
                case "save":
                    writeTasksToFile(tasks, FILE_NAME_TEMP);
                    break;
                case "exit":
                    writeTasksToFile(tasks, FILE_NAME_TEMP);
                    System.out.println("End");
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
        }
    }

    /*
     * Draw the Menu interface */
    public static String[][] addTask(String[][] tasks) {
        //System.out.println(ConsoleColors.GREEN + "add" + ConsoleColors.RESET);
        Scanner scan = new Scanner(System.in);
        boolean readTasks = true;
        String getDescription = "";
        String input = "";
        do {
            System.out.println();
            System.out.print("Please add task description, 0-ends: ");
            getDescription = scan.nextLine();

            System.out.print("Please add task due date: ");
            String getDate = scan.nextLine();

            System.out.print("This is important task, true/false: ");
            String getImportance = scan.nextLine();

            if (getDescription.equals("0") || getDate.equals("0") || getImportance.equals("0")) {
                readTasks = false;

            }
            String[] newTask = new String[3];
            newTask[0] = getDescription;
            newTask[1] = getDate;
            newTask[2] = getImportance;
            tasks = addToDoubleArray(tasks, newTask);
        } while (!scan.nextLine().equals("0"));

        scan.close();

        listTasks(tasks);
        return tasks;
    }

    public static String[][] removeTask(String[][] tasksToRemove) {
        Scanner scan = new Scanner(System.in);
        int number;
        String input = scan.nextLine();

        while (!(Integer.parseInt(input) >= 0 )) {
            System.out.println(ConsoleColors.BLUE + "Please select number to remove:" + ConsoleColors.RESET);
            number = scan.nextInt();
        }
        tasksToRemove = ArrayUtils.remove(tasksToRemove, number);

        return tasksToRemove;
    }

    public static void listTasks(String[][] tasksToList) {

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
        } catch (
                IOException e) {
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
        System.out.println(ConsoleColors.BLUE + "Available options:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("save");
        System.out.println("exit");
    }
}