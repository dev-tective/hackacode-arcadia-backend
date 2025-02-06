package org.gatodev.arcadiaclinica.service.business.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.gatodev.arcadiaclinica.repository.business.IReceiptRepository;
import org.gatodev.arcadiaclinica.service.business.IReceiptService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptServiceImpl implements IReceiptService {

    private final IReceiptRepository receiptRepository;
    private final ICategoryService<TypePayment> typePaymentService;

    public ReceiptServiceImpl(
            IReceiptRepository receiptRepository,
            @Qualifier("typePayment") @Lazy
            ICategoryService<TypePayment> typePaymentService
    ) {
        this.receiptRepository = receiptRepository;
        this.typePaymentService = typePaymentService;
    }

    @Override
    public Receipt addReceipt(List<MedicalAppointment> medicalAppointments) {
        Receipt receipt = new Receipt();

        receipt.setMedicalAppointments(medicalAppointments);

        receipt.setAmount(medicalAppointments.stream()
                .map(ma ->
                        ma.getMedicalService().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        float discount = 0f;
        if (medicalAppointments.get(0).getPatient().getHealthInsurance()) discount += 0.2f;
        if (medicalAppointments.size() > 1) discount += 0.15f;
        receipt.setDiscount(discount);
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt updateReceipt(Receipt receipt) {
        return null;
    }

    @Override
    public Receipt getReceiptById(long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Receipt not found"));
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public List<Receipt> getAllReceiptsByDni(String dni) {
        return receiptRepository.findAllByClient_Dni(dni);
    }
}
