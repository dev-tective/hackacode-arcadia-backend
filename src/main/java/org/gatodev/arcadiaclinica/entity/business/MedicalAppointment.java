package org.gatodev.arcadiaclinica.entity.business;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime attentionDate;

    @Column(nullable = false)
    private LocalDateTime appointmentStart;

    @Column(nullable = false)
    private LocalDateTime appointmentEnd;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalService medicalService;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client patient;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
