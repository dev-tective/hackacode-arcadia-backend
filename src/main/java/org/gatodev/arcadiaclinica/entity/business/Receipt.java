package org.gatodev.arcadiaclinica.entity.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime attentionDate;

    @Column(nullable = false)
    private Boolean paymentReceived;

    private LocalDateTime datePaid;

    private BigDecimal amount;

    private Float discount;

    @ManyToOne
    private TypePayment typePayment;

    @ManyToOne
    private Client client;

    @OneToMany(targetEntity = MedicalAppointment.class)
    private List<MedicalAppointment> medicalAppointments;
}
