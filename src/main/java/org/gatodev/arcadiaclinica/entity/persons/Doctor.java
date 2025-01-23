package org.gatodev.arcadiaclinica.entity.persons;

import java.util.List;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.medical_services.Specialty;
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
    private Specialty specialty;
    
    @OneToMany(mappedBy = "doctor", targetEntity = MedicalAppointment.class)
    private List<MedicalAppointment> medicalAppointments;
}
