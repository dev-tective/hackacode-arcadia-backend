package org.gatodev.arcadiaclinica.entity.medical;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Package")
public class MedicalServicePackage extends MedicalService {
    private LocalDate available = LocalDate.now();

    private LocalDate notAvailable = LocalDate.now().plusMonths(1);

    @ManyToMany(mappedBy = "medicalServicePackages")
    private List<MedicalService> medicalServices;
}
