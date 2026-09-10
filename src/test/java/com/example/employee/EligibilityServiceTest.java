package com.example.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EligibilityServiceTest {

    private final EligibilityService service =
            new EligibilityService();

    @Test
    void normalEmployeeIsEligible() {

        Employee employee =
                new Employee(
                        "E1",
                        "Asha",
                        25,
                        "IT",
                        EmploymentType.ACTIVE,
                        SecurityClearance.CONFIDENTIAL,
                        true
                );

        EligibilityResult result =
                service.evaluate(
                        employee,
                        AccessLevel.CONFIDENTIAL
                );

        assertEquals(
                EligibilityStatus.ELIGIBLE,
                result.status()
        );

        assertTrue(
                result.reasons().isEmpty()
        );
    }

    @Test
    void boundaryAgeExactly21IsAccepted() {

        Employee employee =
                new Employee(
                        "E2",
                        "Bala",
                        21,
                        "HR",
                        EmploymentType.ACTIVE,
                        SecurityClearance.BASIC,
                        true
                );

        EligibilityResult result =
                service.evaluate(
                        employee,
                        AccessLevel.INTERNAL
                );

        assertEquals(
                EligibilityStatus.ELIGIBLE,
                result.status()
        );
    }

    @Test
    void insufficientClearanceProducesConditionalEligibility() {

        Employee employee =
                new Employee(
                        "E3",
                        "Chitra",
                        30,
                        "Finance",
                        EmploymentType.ACTIVE,
                        SecurityClearance.BASIC,
                        true
                );

        EligibilityResult result =
                service.evaluate(
                        employee,
                        AccessLevel.CONFIDENTIAL
                );

        assertEquals(
                EligibilityStatus.CONDITIONALLY_ELIGIBLE,
                result.status()
        );

        assertEquals(
                1,
                result.reasons().size()
        );
    }

    @Test
    void multipleFailuresAreAllReported() {

        Employee employee =
                new Employee(
                        "E4",
                        "Dinesh",
                        19,
                        "Sales",
                        EmploymentType.INACTIVE,
                        SecurityClearance.NONE,
                        false
                );

        EligibilityResult result =
                service.evaluate(
                        employee,
                        AccessLevel.CONFIDENTIAL
                );

        assertEquals(
                EligibilityStatus.NOT_ELIGIBLE,
                result.status()
        );

        // Age + department + employment + ID + clearance
        assertEquals(
                5,
                result.reasons().size()
        );
    }

    @Test
    void invalidEmployeeIdThrowsException() {

        Employee employee =
                new Employee(
                        "",
                        "Esha",
                        25,
                        "IT",
                        EmploymentType.ACTIVE,
                        SecurityClearance.BASIC,
                        true
                );

        assertThrows(
                ValidationException.class,
                () -> service.evaluate(
                        employee,
                        AccessLevel.INTERNAL
                )
        );
    }

    @Test
    void invalidAgeThrowsException() {

        Employee employee =
                new Employee(
                        "E5",
                        "Farah",
                        130,
                        "IT",
                        EmploymentType.ACTIVE,
                        SecurityClearance.BASIC,
                        true
                );

        assertThrows(
                ValidationException.class,
                () -> service.evaluate(
                        employee,
                        AccessLevel.INTERNAL
                )
        );
    }
}
