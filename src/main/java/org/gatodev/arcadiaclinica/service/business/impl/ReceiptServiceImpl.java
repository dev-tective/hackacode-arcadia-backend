package org.gatodev.arcadiaclinica.service.business.impl;

import org.gatodev.arcadiaclinica.DTO.business.ReceiptRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.gatodev.arcadiaclinica.entity.medical.MedicalAttributes;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.repository.business.IReceiptRepository;
import org.gatodev.arcadiaclinica.repository.medical.IMedicalServicePackageRepository;
import org.gatodev.arcadiaclinica.service.business.IMedicalAppointmentService;
import org.gatodev.arcadiaclinica.service.business.IReceiptService;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptServiceImpl implements IReceiptService {

    private final IReceiptRepository receiptRepository;
    private final IMedicalServicePackageRepository packageRepository;
    private final IMedicalAppointmentService medicalAppointmentService;
    private final IClientService clientService;

    public ReceiptServiceImpl(
            IReceiptRepository receiptRepository, IMedicalServicePackageRepository packageRepository,
            IMedicalAppointmentService medicalAppointmentService,
            IClientService clientService
    ) {
        this.receiptRepository = receiptRepository;
        this.packageRepository = packageRepository;
        this.medicalAppointmentService = medicalAppointmentService;
        this.clientService = clientService;
    }

    @Transactional
    @Override
    public Receipt addReceipt(ReceiptRequest request) {
        Client patient = clientService.getClientById(request.idPatient());

        Client client = request.idClient() != null?
                clientService.getClientById(request.idClient()) : null;

        List<MedicalAppointment> mas = request.medicalAppointments().stream()
                .map(mar ->
                        medicalAppointmentService.createMedicalAppointment(mar, patient))
                .toList();

        List<MedicalService> mss = mas.stream().map(MedicalAppointment::getMedicalService).toList();

        boolean pack = packageRepository.findExactPackage(mss, mss.size()).isPresent();

        if (mas.size() > 1 && !pack) {
            throw new RuntimeException("Not medical service package exists");
        }

        if (hasOverlappingAppointments(mas)) {
            throw new RuntimeException("Las citas médicas se solapan.");
        }

        mas.forEach(medicalAppointmentService::addMedicalAppointment);

        Receipt receipt = new Receipt();
        receipt.setClient(client == null? patient: client);

        float discount = 0;

        if (pack) discount += 0.20f;
        if (patient.getHealthInsurance()) discount += 0.15f;

        receipt.setDiscount(discount);
        receipt.setPaymentReceived(false);
        receipt.setAttentionDate(request.attentionDate());
        receipt.setAmount(mss.stream().map(MedicalAttributes::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        receipt.setMedicalAppointments(mas);
        return receiptRepository.save(receipt);
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    private void validateReceipt(ReceiptRequest request) {

    }

    private boolean hasOverlappingAppointments(List<MedicalAppointment> appointments) {
        return appointments.stream().anyMatch(app1 ->
                appointments.stream().anyMatch(app2 ->
                        !app1.equals(app2) &&
                                app1.getAppointmentStart().isBefore(app2.getAppointmentEnd()) &&
                                app2.getAppointmentStart().isBefore(app1.getAppointmentEnd())
                )
        );
    }
}
