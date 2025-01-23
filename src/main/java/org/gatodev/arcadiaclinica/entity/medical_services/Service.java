package org.gatodev.arcadiaclinica.entity.medical_services;

import java.time.Duration;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
