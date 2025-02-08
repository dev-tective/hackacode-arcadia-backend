package org.gatodev.arcadiaclinica.service.business.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.gatodev.arcadiaclinica.repository.business.ITypePaymentRepository;
import org.gatodev.arcadiaclinica.service.business.ITypePaymentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypePaymentServiceImpl implements ITypePaymentService {

    private final ITypePaymentRepository typePaymentRepository;

    public TypePaymentServiceImpl(ITypePaymentRepository typePaymentRepository) {
        this.typePaymentRepository = typePaymentRepository;
    }

    @Override
    public TypePayment addTypePayment(TypePayment typePayment) {
        return typePaymentRepository.save(typePayment);
    }

    @Override
    public TypePayment updateTypePayment(TypePayment typePayment) {
        return typePaymentRepository.save(typePayment);
    }

    @Override
    public TypePayment getTypePaymentById(long id) {
        return typePaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type payment not found"));
    }

    @Override
    public List<TypePayment> getAllTypePayments() {
        return typePaymentRepository.findAll();
    }

    @Override
    public void deactivateMedicalService(long id) {
        TypePayment typePayment = getTypePaymentById(id);
        typePayment.setState(false);
        typePaymentRepository.save(typePayment);
    }

    @Override
    public void activateMedicalService(long id) {
        TypePayment typePayment = getTypePaymentById(id);
        typePayment.setState(true);
        typePaymentRepository.save(typePayment);
    }
}
