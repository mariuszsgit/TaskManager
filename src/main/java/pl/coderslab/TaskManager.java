package pl.coderslab;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "appFile/tasks.csv";


    public static void main(String[] args) {

        String[][] tasks;
        tasks = getTasks(FILE_NAME);

        listTasks(tasks);
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
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.GREEN + "add");
        System.out.println("Please add task description");
    }

    public static void removeTask() {
        System.out.println(ConsoleColors.GREEN + "remove");
        System.out.println(ConsoleColors.BLUE + "Please select number to remove:" + ConsoleColors.RESET);

    }

    public static void listTasks(String[][] tasksToList) {
        System.out.println(ConsoleColors.GREEN + "list\n" +
                ConsoleColors.BLUE + "List of available tasks:" + ConsoleColors.RESET);
        for (int i = 0; i < tasksToList.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < tasksToList[i].length; j++) {

                if (j != tasksToList[i].length - 1) {
                    System.out.print(tasksToList[i][j] + " ");
                }
            }
            if (Boolean.parseBoolean(tasksToList[i][2])) {
                System.out.print(" Status:" + ConsoleColors.RED +" Important" + ConsoleColors.RESET);
            } else {
                System.out.print(" Status: Normal");
            }
            System.out.println();
        }
    }

    public static String[][] getTasks(String fileName) {
        String[][] tasksArray = new String[0][0];

        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            System.out.println("tasks.cvs file missing");
            System.exit(0);
        }

        try {
            int i = 0;
            for (String fileLines : Files.readAllLines(filePath)) {
                tasksArray = addToArray(tasksArray, fileLines.trim().split(", "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasksArray;
    }

    public static String[][] addToArray(String[][] tab, String[] element) {
        String[][] newTab = Arrays.copyOf(tab, tab.length + 1);
        newTab[tab.length] = element;
        return newTab;
    }

    public static void drawMenuInterface() {
        System.out.println(ConsoleColors.BLUE + "Available options:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }
}