package com.example.employee;

import java.util.List;

public record EligibilityResult(
        String employeeName,
        EligibilityStatus status,
        List<String> reasons) {

    public EligibilityResult {
        reasons = List.copyOf(reasons);
    }

    public boolean isRejected() {
        return status == EligibilityStatus.NOT_ELIGIBLE;
    }
}
