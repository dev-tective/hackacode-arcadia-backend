package com.arcadia.clinica.entity.business_services;

import com.arcadia.clinica.entity.business.AppointmentItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(targetEntity = AppointmentItem.class, mappedBy = "item")
    private List<AppointmentItem> appointmentItems;




}
