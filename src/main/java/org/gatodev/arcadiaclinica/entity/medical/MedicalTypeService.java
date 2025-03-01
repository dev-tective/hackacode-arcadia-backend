package org.gatodev.arcadiaclinica.entity.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicalTypeService extends BaseAttributes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

