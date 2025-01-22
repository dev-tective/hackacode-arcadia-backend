package com.arcadia.clinica.entity.persons;

import com.arcadia.clinica.entity.business.Receipt;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends User {
    @OneToMany(targetEntity = Receipt.class, mappedBy = "client")
    private List<Receipt> receipts;
}
