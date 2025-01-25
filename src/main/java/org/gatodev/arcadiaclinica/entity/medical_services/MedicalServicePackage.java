package org.gatodev.arcadiaclinica.entity.medical_services;

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
    @Column(nullable = false)
    private LocalDate available = LocalDate.now();

    @Column(nullable = false)
    private LocalDate notAvailable = LocalDate.now().plusMonths(1);

    @ManyToMany(mappedBy = "medicalServicePackages")
    @JoinColumn(nullable = false)
    private List<MedicalService> services;
}
