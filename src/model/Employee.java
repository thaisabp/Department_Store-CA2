package model;

import enums.Department;
import enums.ManagerType;

// model class representing an employee in the department store
public class Employee {
    private String firstName;
    private String lastName;
    private Department department;
    private ManagerType managerType;
    private String jobTitle;
    private String company;
    private double salary;

    // constructor receives raw data from the text file
    public Employee(String firstName, String lastName, String rawDepartment, String rawPosition, String jobTitle, String company, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.company = company;
        this.salary = salary;


        this.department = parseDepartment(rawDepartment);
        this.managerType = parseManagerType(rawPosition);
    }

    // helper method to safely convert String to department enum
    private Department parseDepartment(String dept) {
        try {
            return Department.valueOf(dept.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return Department.LOGISTICS; // Fallback in case the data is invalid
        }
    }

    // helper method to safely convert String to ManagerType Enum
    private ManagerType parseManagerType(String position) {
        try {
            return ManagerType.valueOf(position.trim().toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return ManagerType.STAFF; // fallback in case the data is invalid
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public double getSalary() {
        return salary;
    }

    // formats the output for the terminal
    @Override
    public String toString() {
        return String.format ("%-20s | %-20s | %-20s | %-20s | %-20s | %.2f ", getFullName(), getManagerType(), getDepartment(), getJobTitle(), getCompany(), getSalary());
    }
}