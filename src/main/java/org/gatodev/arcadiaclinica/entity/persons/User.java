package org.gatodev.arcadiaclinica.entity.persons;

import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import org.gatodev.arcadiaclinica.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active;

    @PrePersist
    public void prePersist() {
        if (role == null) role = Role.CLIENT;
        if (active == null) active = true;
    }
}
