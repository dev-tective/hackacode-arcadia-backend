package org.gatodev.arcadiaclinica.entity.persons;

import java.util.List;
import jakarta.persistence.*;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gatodev.arcadiaclinica.entity.business.Receipt;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String allergies;

    private Integer age;

    @Column(nullable = false)
    private Boolean healthInsurance = false;

    @OneToMany(targetEntity = Receipt.class, mappedBy = "client")
    private List<Receipt> receipts;

    @OneToMany(targetEntity = MedicalAppointment.class, mappedBy = "patient")
    private List<MedicalAppointment> medicalAppointments;
}
