package com.arcadia.clinica.DTOS.persons;

import com.arcadia.clinica.entity.persons.Person;
import com.arcadia.clinica.util.DayOff;
import com.arcadia.clinica.util.Role;
import lombok.*;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends Person {
    private Float salary;
    private DayOff dayOff;
    private LocalTime entranceWork;
    private LocalTime exitWork;
    private Role role;
}
