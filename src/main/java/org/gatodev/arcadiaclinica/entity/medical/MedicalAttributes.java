package org.gatodev.arcadiaclinica.entity.medical;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class MedicalAttributes extends BaseAttributes {
    @Column(nullable = false, unique = true, length = 6)
    private String code;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private BigDecimal price;
}
