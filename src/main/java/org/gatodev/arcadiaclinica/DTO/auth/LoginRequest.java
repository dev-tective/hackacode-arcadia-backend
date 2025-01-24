package org.gatodev.arcadiaclinica.DTO.auth;

public record LoginRequest(
        String dni,
        String email,
        String password
) {
}
