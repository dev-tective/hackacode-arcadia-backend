package org.gatodev.arcadiaclinica.entity.persons;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;
    
    @Column(nullable = false, unique = true)
    private String email;

    private String numberPhone;
    private String address;
    private LocalDate birthDate;
}
