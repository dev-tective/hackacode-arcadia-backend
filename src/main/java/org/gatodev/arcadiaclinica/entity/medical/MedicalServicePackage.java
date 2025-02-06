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
public class MedicalServicePackage extends MedicalAttributes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate available;

    @Column(nullable = false)
    private LocalDate notAvailable;

    @ManyToMany
    private List<MedicalService> medicalServices;
}
