package org.gatodev.arcadiaclinica.DTO.persons;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.persons.Person;
import org.gatodev.arcadiaclinica.util.enums.DayOff;
import java.time.LocalTime;

@Getter
@Setter
public class DoctorDTO extends Person {
    @NotNull
    private LocalTime entranceWork;

    @NotNull
    private LocalTime exitWork;

    @NotNull
    private DayOff dayOff;

    @NotNull
    private Float salary;

    @NotNull
    private MedicalSpecialty medicalSpecialty;
}
