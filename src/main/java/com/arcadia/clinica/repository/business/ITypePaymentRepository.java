package com.arcadia.clinica.repository.business;

import com.arcadia.clinica.entity.business.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypePaymentRepository extends JpaRepository<TypePayment, Integer> {
}
