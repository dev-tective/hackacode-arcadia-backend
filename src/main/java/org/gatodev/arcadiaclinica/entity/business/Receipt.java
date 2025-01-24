package org.gatodev.arcadiaclinica.entity.business;

import java.math.BigDecimal;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    private Float discount;

    @OneToOne(targetEntity = MedicalAppointment.class)
    private MedicalAppointment medicalAppointment;

    @ManyToOne
    private TypePayment typePayment;

    @ManyToOne
    private Client client;
}
