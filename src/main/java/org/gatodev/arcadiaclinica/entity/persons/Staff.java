package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalTime;
import lombok.*;
import org.gatodev.arcadiaclinica.util.enums.DayOff;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOff dayOff = DayOff.SUNDAY;

    @Column(nullable = false)
    private Float salary = 1125.0f;
}
