package com.example.employee;

public enum SecurityClearance {

    NONE(0),
    BASIC(1),
    CONFIDENTIAL(2),
    SECRET(3);

    private final int level;

    SecurityClearance(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
