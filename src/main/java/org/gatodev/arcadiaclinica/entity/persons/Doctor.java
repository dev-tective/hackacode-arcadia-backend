package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gatodev.arcadiaclinica.util.enums.DayOff;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalTime entranceWork;

    @NotNull
    @Column(nullable = false)
    private LocalTime exitWork;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOff dayOff;

    @NotNull
    @Column(nullable = false)
    private Float salary;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalSpecialty medicalSpecialty;

    @OneToMany(mappedBy = "doctor", targetEntity = MedicalAppointment.class)
    private List<MedicalAppointment> medicalAppointments;
}
