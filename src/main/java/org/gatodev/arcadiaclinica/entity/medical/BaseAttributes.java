package org.gatodev.arcadiaclinica.entity.medical;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseAttributes {
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private Boolean state = true;

    public void isActivated() {
        if (!state) {
            throw new RuntimeException(name + " not activated");
        }
    }
}
