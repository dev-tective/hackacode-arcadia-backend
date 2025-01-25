package org.gatodev.arcadiaclinica.entity.persons;

import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.gatodev.arcadiaclinica.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {
    @NotBlank(message = "La contraseña no puede ser vacía.")
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    @Column(nullable = false)
    private Boolean active = true;
}
