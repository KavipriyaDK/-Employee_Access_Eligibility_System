package com.example.employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EligibilityService service = new EligibilityService();

        List<Employee> employees = List.of(
                new Employee("E001", "Anita", 25, "IT",
                        EmploymentType.ACTIVE, SecurityClearance.CONFIDENTIAL, true),
                new Employee("E002", "Bala", 20, "IT",
                        EmploymentType.ACTIVE, SecurityClearance.SECRET, true),
                new Employee("E003", "Chitra", 30, "Sales",
                        EmploymentType.INACTIVE, SecurityClearance.BASIC, false),
                new Employee("E004", "Deepak", 28, "HR",
                        EmploymentType.ACTIVE, SecurityClearance.BASIC, true)
        );

        for (Employee employee : employees) {
            AccessLevel requested = employee.getName().equals("Deepak")
                    ? AccessLevel.CONFIDENTIAL
                    : AccessLevel.CONFIDENTIAL;

            EligibilityResult result = service.evaluate(employee, requested);
            System.out.println(employee.getName() + " -> " + result.status());
            result.reasons().forEach(reason -> System.out.println("  - " + reason));
        }
    }
}
