package org.gatodev.arcadiaclinica.entity.medical;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class MedicalService extends BaseAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción no puede ser vacía.")
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Duration duration = Duration.ofHours(1);

    @Column(nullable = false)
    private BigDecimal price = BigDecimal.valueOf(30);

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalTypeService medicalTypeService;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MedicalSpecialty medicalSpecialty;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    private List<MedicalServicePackage> medicalServicePackages;
}
