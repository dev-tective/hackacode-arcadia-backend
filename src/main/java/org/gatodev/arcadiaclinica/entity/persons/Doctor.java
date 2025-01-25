package org.gatodev.arcadiaclinica.entity.persons;

import java.util.List;

import jakarta.persistence.JoinColumn;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.medical_services.MedicalSpecialty;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Doctor extends Staff {
    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalSpecialty medicalSpecialty;
    
    @OneToMany(mappedBy = "doctor", targetEntity = MedicalAppointment.class)
    private List<MedicalAppointment> medicalAppointments;
}
