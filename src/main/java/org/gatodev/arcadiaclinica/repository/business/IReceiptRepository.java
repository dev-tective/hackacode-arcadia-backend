package org.gatodev.arcadiaclinica.repository.business;

import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAllByClient_Dni(String dni);
}
