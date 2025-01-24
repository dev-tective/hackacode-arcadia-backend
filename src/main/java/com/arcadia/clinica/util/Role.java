package com.arcadia.clinica.util;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    RECEPTION("reception"),
    NURSE("nurse"),
    DOCTOR("doctor"),
    CLIENT("client");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}

