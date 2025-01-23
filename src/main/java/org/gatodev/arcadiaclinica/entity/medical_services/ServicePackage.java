package org.gatodev.arcadiaclinica.entity.medical_services;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
