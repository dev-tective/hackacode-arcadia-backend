package org.gatodev.arcadiaclinica.entity.persons;

import java.util.List;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(targetEntity = MedicalAppointment.class, mappedBy = "patient")
    private List<MedicalAppointment> medicalAppointments;
}
