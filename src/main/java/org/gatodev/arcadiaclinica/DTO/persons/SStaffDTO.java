package org.gatodev.arcadiaclinica.DTO.persons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gatodev.arcadiaclinica.util.enums.DayOff;
import org.gatodev.arcadiaclinica.util.enums.Role;
import java.time.LocalTime;

@Getter
@Setter
public class SStaffDTO extends SUserDTO {
    private LocalTime entranceWork;
    private LocalTime exitWork;
    private DayOff dayOff;
    private Float salary;

    SStaffDTO(String password, Role role, Boolean active) {
        super(password, role, active);
    }
}
