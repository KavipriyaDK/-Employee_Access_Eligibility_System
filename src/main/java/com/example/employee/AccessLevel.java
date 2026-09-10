package com.example.employee;

public enum AccessLevel {

    PUBLIC(0),
    INTERNAL(1),
    CONFIDENTIAL(2);

    private final int requiredClearance;

    AccessLevel(int requiredClearance) {
        this.requiredClearance = requiredClearance;
    }

    public int getRequiredClearance() {
        return requiredClearance;
    }
}
