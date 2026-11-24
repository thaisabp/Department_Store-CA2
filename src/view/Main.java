package view;

import enums.MenuOption;
import model.Employee;
import model.EmployeeManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private EmployeeManager manager;
    private Scanner scanner;

    public Main() {
        this.manager = new EmployeeManager();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("DEPARTMENT STORE SYSTEM STARTING");

        //1- try to load data from the file
        boolean success = manager.fromFile("Applicants_Form.txt");

        if (success) {
            System.out.println("Success! " + manager.getEmployeeCount() + " employees loaded.\n");
        } else {
            System.out.println("ERROR: Failed to load data. Please check if 'Applicants_Form.txt' is in the project root.\n");
            return; //stop the program if it fails
        }

        // 2- main Menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            String input = scanner.nextLine().trim();

            try {
                MenuOption option;
                switch (input) {
                    case "1":
                        option = MenuOption.SORT;
                        break;
                    case "2":
                        option = MenuOption.SEARCH;
                        break;
                    case "3":
                        option = MenuOption.ADD_RECORDS;
                        break;
                    case "4":
                        option = MenuOption.CREATE_TREE;
                        break;
                    case "5":
                        option = MenuOption.EXIT;
                        break;
                    default:
                        //if the user type anything else
                        throw new IllegalArgumentException();
                }

                running = handleOption(option);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid option. Please choose a number from 1 to 5.");
            }
        }

        System.out.println("Exiting the system. See you later!");
        scanner.close();
    }

    //displays the formatted menu in the terminal
    private void displayMenu() {
        System.out.println("        DEPARTMENT STORE SYSTEM          ");
        System.out.println("1 - SORT          (Sort employees)");
        System.out.println("2 - SEARCH        (Search employee)");
        System.out.println("3 - ADD RECORDS   (Add new record)");
        System.out.println("4 - CREATE TREE   (Create Binary Tree)");
        System.out.println("5 - EXIT          (Exit system)");
        System.out.print("Choose an option: ");
    }

    //processes the user's choice
    private boolean handleOption(MenuOption option) {
        switch (option) {
            case SORT:
                manager.sortEmployees();
                System.out.println("\nEMPLOYEES SORTED BY NAME");
                for (Employee emp : manager.getEmployeeList()) {
                    System.out.println(emp);
                }
                System.out.println("--------------------------------\n");
                return true; //continues the loop
            case SEARCH:
                System.out.print("\nEnter the name to search: ");
                String searchInput = scanner.nextLine().trim();

                //call the search method
                List<Employee> foundEmployees = manager.searchEmployees(searchInput);

                if (foundEmployees.isEmpty()) {
                    System.out.println("No employees found with this name.\n");
                } else {
                    System.out.println("\nSEARCH RESULTS");
                    for (Employee emp : foundEmployees) {
                        System.out.println(emp); //calls the overridden toString() method for formatted output
                    }
                    System.out.println("----------------------\n");
                }
                return true; //continues the loop
            case ADD_RECORDS:
                System.out.println("\n[ADD RECORDS] functionality will be implemented in the next commit!\n");
                return true;
            case CREATE_TREE:
                System.out.println("\n[CREATE TREE] functionality will be implemented in the next commit!\n");
                return true;
            case EXIT:
                return false; //breaks the loop and ends the program
            default:
                return true;
        }
    }

    //entry point of the program
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}