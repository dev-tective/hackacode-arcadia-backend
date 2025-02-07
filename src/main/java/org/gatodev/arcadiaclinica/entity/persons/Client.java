package org.gatodev.arcadiaclinica.entity.persons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String allergies;

    @Column(length = 3)
    private Integer age;

    @Column(nullable = false)
    private Boolean healthInsurance = false;
}
