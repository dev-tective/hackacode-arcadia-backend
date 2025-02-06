package org.gatodev.arcadiaclinica.DTO.auth;

public record UpdateRequest(
        String oldField,
        String newField
) {
}
