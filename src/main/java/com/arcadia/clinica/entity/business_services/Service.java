package com.arcadia.clinica.entity.business_services;

import com.arcadia.clinica.entity.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service extends BaseService {
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
    private List<ServicePackage> servicePackages;
}
