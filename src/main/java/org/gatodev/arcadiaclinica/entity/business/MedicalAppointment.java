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
    private LocalDateTime attentionDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime appointmentStart = LocalDateTime.now().plusMinutes(30);

    @Column(nullable = false)
    private LocalDateTime appointmentEnd = LocalDateTime.now().plusHours(1).plusMinutes(30);

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalService medicalService;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client patient;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Receipt receipt;
}
