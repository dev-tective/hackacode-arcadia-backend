package com.arcadia.clinica.entity.business;

import com.arcadia.clinica.entity.persons.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime attentionDate;
    @ManyToOne
    private Patient patient;

    @OneToMany(targetEntity = AppointmentItem.class, mappedBy = "appointment")
    private List<AppointmentItem> appointmentItems;
}
