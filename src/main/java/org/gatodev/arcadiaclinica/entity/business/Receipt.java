package org.gatodev.arcadiaclinica.entity.business;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gatodev.arcadiaclinica.entity.persons.Client;

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

    @OneToMany(targetEntity = MedicalAppointment.class, mappedBy = "receipt")
    private List<MedicalAppointment> medicalAppointments;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TypePayment typePayment;

    @ManyToOne
    private Client client;
}
