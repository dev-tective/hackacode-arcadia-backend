package org.gatodev.arcadiaclinica.DTO.persons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gatodev.arcadiaclinica.util.enums.Role;

@Getter
@Setter
public class SUserDTO extends PersonDTO {
    private String password;
    private Role role;
    private Boolean active;

    public SUserDTO(String password, Role role, Boolean active) {
        super();
    }
}
