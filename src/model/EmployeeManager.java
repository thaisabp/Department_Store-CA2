package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employeeList;

    public EmployeeManager() {
        this.employeeList = new ArrayList<>();
    }

    // method to read the text file and add to the employeeList
    public boolean fromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            //read and ignore the first line (the header)
            String header = br.readLine();

            String line;
            //read the file line by line until the end
            while ((line = br.readLine()) != null) {
                // Split the line by comma
                String[] data = line.split(",");

                //ensure the line has all 9 expected columns to avoid errors
                if (data.length >= 9) {
                    String firstName = data[0].trim();
                    String lastName = data[1].trim();
                    // data[2] is Gender (ignored)
                    // data[3] is Email (ignored)
                    double salary = Double.parseDouble(data[4].trim());
                    String rawDepartment = data[5].trim();
                    String rawPosition = data[6].trim();
                    String jobTitle = data[7].trim();
                    String company = data[8].trim();

                    //create a new Employee object and add it to the list
                    Employee emp = new Employee(firstName, lastName, rawDepartment, rawPosition, jobTitle, company, salary);
                    employeeList.add(emp);
                }
            }
            return true; //if it succeeds
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return false; // Failure
        } catch (NumberFormatException e) {
            System.out.println("Error parsing salary data: " + e.getMessage());
            return false; // if it fails
        }
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getEmployeeCount() {
        return employeeList.size();
    }
}