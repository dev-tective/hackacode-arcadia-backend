package com.arcadia.clinica.entity.business;

import com.arcadia.clinica.entity.business_services.BaseService;
import com.arcadia.clinica.entity.persons.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime appointmentStart;
    @Column(nullable = false)
    private LocalDateTime appointmentEnd;

    @ManyToOne
    private BaseService item;
    @ManyToOne
    private Appointment appointment;
    @ManyToOne
    private Doctor doctor;
}
