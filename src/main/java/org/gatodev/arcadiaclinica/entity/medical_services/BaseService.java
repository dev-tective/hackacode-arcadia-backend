package org.gatodev.arcadiaclinica.entity.medical_services;

import java.math.BigDecimal;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //  Union de code con abreviatura solo para service
    //  Service package es la suma de todos los code service
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal price;
}
