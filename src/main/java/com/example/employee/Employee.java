package com.example.employee;

public class Employee {

    private final String employeeId;
    private final String name;
    private final int age;
    private final String department;
    private final EmploymentType employmentType;
    private final SecurityClearance securityClearance;
    private final boolean idValid;

    public Employee(
            String employeeId,
            String name,
            int age,
            String department,
            EmploymentType employmentType,
            SecurityClearance securityClearance,
            boolean idValid) {

        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.employmentType = employmentType;
        this.securityClearance = securityClearance;
        this.idValid = idValid;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public SecurityClearance getSecurityClearance() {
        return securityClearance;
    }

    public boolean isIdValid() {
        return idValid;
    }
}
