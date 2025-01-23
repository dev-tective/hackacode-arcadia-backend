package org.gatodev.arcadiaclinica.entity.business;

import java.time.LocalDateTime;
import org.gatodev.arcadiaclinica.entity.medical_services.BaseService;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.entity.persons.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private BaseService item;

    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;
}
