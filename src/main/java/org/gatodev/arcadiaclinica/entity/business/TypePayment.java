package org.gatodev.arcadiaclinica.entity.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gatodev.arcadiaclinica.entity.medical.BaseAttributes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TypePayment extends BaseAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
