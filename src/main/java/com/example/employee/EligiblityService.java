package com.example.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EligibilityService {

    // Minimum age requirement
    private static final int MIN_AGE = 21;

    // Authorized departments
    private static final Set<String> AUTHORIZED_DEPARTMENTS =
            Set.of(
                    "IT",
                    "HR",
                    "FINANCE",
                    "ADMINISTRATION"
            );

    public EligibilityResult evaluate(
            Employee employee,
            AccessLevel requestedAccess) {

        validate(employee, requestedAccess);

        List<String> reasons = new ArrayList<>();

        // Check age
        if (employee.getAge() < MIN_AGE) {
            reasons.add(
                    "Employee must be at least 21 years old."
            );
        }

        // Check department
        if (!AUTHORIZED_DEPARTMENTS.contains(
                employee.getDepartment().trim().toUpperCase())) {

            reasons.add(
                    "Employee department is not authorized."
            );
        }

        // Check employment
        if (employee.getEmploymentType()
                != EmploymentType.ACTIVE) {

            reasons.add(
                    "Employee must have active employment status."
            );
        }

        // Check employee ID
        if (!employee.isIdValid()) {
            reasons.add(
                    "Employee ID is invalid."
            );
        }

        // Check security clearance
        boolean clearanceFailure =
                employee.getSecurityClearance().getLevel()
                        < requestedAccess.getRequiredClearance();

        if (clearanceFailure) {
            reasons.add(
                    "Security clearance is insufficient " +
                    "for the requested access level."
            );
        }

        /*
         * Core failures:
         * age
         * department
         * employment
         * employee ID
         */
        boolean coreFailure = reasons.stream().anyMatch(
                reason ->
                        reason.contains("21 years")
                        || reason.contains("department")
                        || reason.contains("active employment")
                        || reason.contains("Employee ID")
        );

        EligibilityStatus status;

        if (coreFailure) {

            status = EligibilityStatus.NOT_ELIGIBLE;

        } else if (clearanceFailure) {

            status = EligibilityStatus.CONDITIONALLY_ELIGIBLE;

        } else {

            status = EligibilityStatus.ELIGIBLE;
        }

        return new EligibilityResult(
                employee.getName(),
                status,
                reasons
        );
    }

    private void validate(
            Employee employee,
            AccessLevel requestedAccess) {

        if (employee == null) {
            throw new ValidationException(
                    "Employee cannot be null."
            );
        }

        if (requestedAccess == null) {
            throw new ValidationException(
                    "Requested access cannot be null."
            );
        }

        if (employee.getEmployeeId() == null
                || employee.getEmployeeId().isBlank()) {

            throw new ValidationException(
                    "Employee ID cannot be blank."
            );
        }

        if (employee.getName() == null
                || employee.getName().isBlank()) {

            throw new ValidationException(
                    "Employee name cannot be blank."
            );
        }

        if (employee.getAge() < 0
                || employee.getAge() > 120) {

            throw new ValidationException(
                    "Employee age must be between 0 and 120."
            );
        }

        if (employee.getDepartment() == null
                || employee.getDepartment().isBlank()) {

            throw new ValidationException(
                    "Department cannot be blank."
            );
        }

        if (employee.getEmploymentType() == null) {

            throw new ValidationException(
                    "Employment type cannot be null."
            );
        }

        if (employee.getSecurityClearance() == null) {

            throw new ValidationException(
                    "Security clearance cannot be null."
            );
        }
    }
}
