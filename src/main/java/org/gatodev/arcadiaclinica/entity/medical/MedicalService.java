package org.gatodev.arcadiaclinica.entity.medical;

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
public class MedicalService extends MedicalAttributes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalTypeService medicalTypeService;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalSpecialty medicalSpecialty;
}
