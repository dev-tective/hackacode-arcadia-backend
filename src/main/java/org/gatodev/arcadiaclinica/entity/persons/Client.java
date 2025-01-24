package org.gatodev.arcadiaclinica.entity.persons;

import java.util.List;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends User {
    @OneToMany(targetEntity = Receipt.class, mappedBy = "client")
    private List<Receipt> receipts;
}




















