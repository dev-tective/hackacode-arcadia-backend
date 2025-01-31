package org.gatodev.arcadiaclinica.repository.business;

import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptRepository extends JpaRepository<Receipt, Long> {
}
