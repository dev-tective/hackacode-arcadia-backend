package com.arcadia.clinica.entity.business_services;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderMethodName = "medicalServiceBuilder")
public class MedicalService extends BaseService {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Duration duration;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TypeService typeService;
    @ManyToOne
    private Specialty specialty;

    @ManyToMany
    private List<MedicalServicePackage> medicalServicePackages;

    public static MedicalServiceBuilder builder() {
        return medicalServiceBuilder();
    }

    public static class MedicalServiceBuilder {
        private String code;
        private BigDecimal price;

        public MedicalServiceBuilder code(String code) {
            this.code = code;
            return this;
        }

        public MedicalServiceBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MedicalService build() {
            MedicalService medicalService = new MedicalService();
            medicalService.setCode(this.code);
            medicalService.setPrice(this.price);
            medicalService.setName(this.name);
            medicalService.setDescription(this.description);
            medicalService.setDuration(this.duration);
            medicalService.setTypeService(this.typeService);
            medicalService.setSpecialty(this.specialty);
            medicalService.setMedicalServicePackages(this.medicalServicePackages);
            return medicalService;
        }
    }


}
