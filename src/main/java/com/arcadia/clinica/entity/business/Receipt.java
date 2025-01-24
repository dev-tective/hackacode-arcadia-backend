package com.arcadia.clinica.entity.business;

import com.arcadia.clinica.entity.persons.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private Boolean paymentReceived;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    private Float discount;

    @OneToOne(targetEntity = Appointment.class)
    private Appointment appointment;
    @ManyToOne
    private TypePayment typePayment;
    @ManyToOne
    private Client client;
}
