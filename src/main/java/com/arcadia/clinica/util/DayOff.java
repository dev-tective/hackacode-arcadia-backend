package com.arcadia.clinica.util;

import lombok.Getter;

@Getter
public enum DayOff {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DayOff(String day) {
        this.day = day;
    }
}

