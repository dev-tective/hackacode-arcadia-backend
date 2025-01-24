package com.arcadia.clinica.entity.business_services;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedicalServicePackage extends BaseService {
    @Column(nullable = false)
    private LocalDate available;
    @Column(nullable = false)
    private LocalDate notAvailable;
    @Column(nullable = false)
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "medical_service_package_services",
            joinColumns = @JoinColumn(name = "service_package_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_service_id")
    )
    private List<MedicalService> medicalServices;


    public static MedicalServicePackage.MedicalServicePackageBuilder builder() {
        return new MedicalServicePackageBuilder();
    }

    public static class MedicalServicePackageBuilder {
        private String code;
        private BigDecimal price;

        public MedicalServicePackage.MedicalServicePackageBuilder code(String code) {
            this.code = code;
            return this;
        }

        public MedicalServicePackage.MedicalServicePackageBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MedicalServicePackage build() {
            MedicalServicePackage medicalServicePackage = new MedicalServicePackage();
            medicalServicePackage.setCode(this.code);
            medicalServicePackage.setPrice(this.price);
            medicalServicePackage.setAvailable(this.available);
            medicalServicePackage.setNotAvailable(this.notAvailable);
            medicalServicePackage.setStatus(this.status);
            return medicalServicePackage;
        }
    }

}
