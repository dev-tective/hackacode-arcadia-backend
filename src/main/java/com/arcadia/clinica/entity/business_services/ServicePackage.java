package com.arcadia.clinica.entity.business_services;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServicePackage extends BaseService {
    @Column(nullable = false)
    private LocalDate available;
    @Column(nullable = false)
    private LocalDate notAvailable;
    @Column(nullable = false)
    private Boolean state;

    @ManyToMany(mappedBy = "servicePackages")
    private List<Service> services;
}
