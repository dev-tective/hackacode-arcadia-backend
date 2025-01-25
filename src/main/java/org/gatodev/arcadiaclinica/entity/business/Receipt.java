package org.gatodev.arcadiaclinica.entity.business;

import java.math.BigDecimal;

import jakarta.persistence.*;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean paymentReceived;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Float discount = 0f;

    @OneToOne(targetEntity = MedicalAppointment.class)
    @JoinColumn(nullable = false)
    private MedicalAppointment medicalAppointment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TypePayment typePayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;
}
