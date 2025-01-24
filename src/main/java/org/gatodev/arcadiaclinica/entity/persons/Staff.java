package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalTime;
import org.gatodev.arcadiaclinica.util.enums.DayOff;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff extends User {
    @Column(nullable = false)
    private LocalTime entranceWork;

    @Column(nullable = false)
    private LocalTime exitWork;

    @Enumerated(EnumType.STRING)
    private DayOff dayOff;

    private Float salary;

    public void prePersist() {
        if (salary == null) salary = 1125.0f;
        if (dayOff == null) dayOff = DayOff.SUNDAY;
    }
}
