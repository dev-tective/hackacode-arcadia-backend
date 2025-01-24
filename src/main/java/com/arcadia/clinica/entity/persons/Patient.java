package com.arcadia.clinica.entity.persons;

import com.arcadia.clinica.entity.business.Appointment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends Person {
    private String allergies;
    private Integer age;
    @Column(nullable = false)
    private Boolean healthInsurance;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "patient")
    private List<Appointment> appointments;
}
