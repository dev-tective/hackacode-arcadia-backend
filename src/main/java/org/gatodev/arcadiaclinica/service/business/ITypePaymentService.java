package org.gatodev.arcadiaclinica.service.business;

import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import java.util.List;

public interface ITypePaymentService {
    TypePayment addTypePayment(TypePayment typePayment);

    TypePayment updateTypePayment(TypePayment typePayment);

    TypePayment getTypePaymentById(long id);

    List<TypePayment> getAllTypePayments();

    void deactivateMedicalService(long id);

    void activateMedicalService(long id);
}
