package pl.coderslab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        drawMenuInterface();
        Scanner scanner = new Scanner(System.in);

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
                    listTask();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }
        }

    }

    /*
     * Draw the Menu interface */
    public static void drawMenuInterface() {
        System.out.println(ConsoleColors.BLUE + "Available options:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static void addTask() {
        System.out.println("add\nPlease add task description");
    }

    public static void removeTask() {
        System.out.println("remove\nPlease select number to remove:");

    }

    public static void listTask() {
        System.out.println("list\nList of available tasks:");

    }


}