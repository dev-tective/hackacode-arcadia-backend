package org.gatodev.arcadiaclinica.entity.persons;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.gatodev.arcadiaclinica.entity.IFieldsValidate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements IFieldsValidate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El email no puede estar vacío.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña no puede ser vacía.")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToMany
    private List<Role> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private Boolean isAdmin = false;
}
