package org.gatodev.arcadiaclinica.repository.business;

import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypePaymentRepository extends JpaRepository<TypePayment, Long> {
    boolean existsByName(String name);
}
