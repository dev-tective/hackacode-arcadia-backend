package org.gatodev.arcadiaclinica.service.business.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.business.MedicalAppointmentRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.business.IMedicalAppointmentRepository;
import org.gatodev.arcadiaclinica.service.business.IMedicalAppointmentService;
import org.gatodev.arcadiaclinica.service.business.IReceiptService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceService;
import org.gatodev.arcadiaclinica.service.persons.IClientService;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalAppointmentServiceImpl implements IMedicalAppointmentService {

    private final IMedicalAppointmentRepository medicalAppointmentRepository;
    private final IDoctorService doctorService;
    private final IClientService clientService;
    private final IMedicalServiceService medicalServiceService;
    private final IReceiptService receiptService;

    public MedicalAppointmentServiceImpl(
            IMedicalAppointmentRepository medicalAppointmentRepository,
            @Lazy IDoctorService doctorService,
            @Lazy IClientService clientService,
            @Lazy IMedicalServiceService medicalServiceService,
            @Lazy IReceiptService receiptService
    ) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
        this.doctorService = doctorService;
        this.clientService = clientService;
        this.medicalServiceService = medicalServiceService;
        this.receiptService = receiptService;
    }

    @Transactional
    @Override
    public MedicalAppointment addMedicalAppointment(MedicalAppointmentRequest request) {
        MedicalService ms = medicalServiceService.getMedicalServiceById(request.idMedicalService());
        if (!ms.getState()) throw new RuntimeException("Medical service not available");

        Doctor d = doctorService.getEntityById(request.idDoctor());
        if (!d.getEnabled()) throw new RuntimeException("Doctor is not enabled");

        Client c = clientService.getEntityById(request.idPatient());
        if (!c.getEnabled()) throw new RuntimeException("Client is not enabled");

        MedicalAppointment ma = new MedicalAppointment();
        ma.setAttentionDate(LocalDateTime.now());
        ma.setAppointmentStart(request.appointmentStart());
        ma.setAppointmentEnd(calculateTime(ma));
        ma.setPatient(c);
        ma.setDoctor(d);
        ma.setMedicalService(ms);
        receiptService.addReceipt(Collections.singletonList(ma));
        return medicalAppointmentRepository.save(ma);
    }

    @Transactional
    @Override
    public List<MedicalAppointment> addMedicalAppointment(List<MedicalAppointmentRequest> requests) {
        List<MedicalAppointment> medicalAppointments = requests.stream()
                .map(this::addMedicalAppointment)
                .toList();

        Set<Client> singlePatient = medicalAppointments.stream().map(MedicalAppointment::getPatient)
                .collect(Collectors.toSet());

        if (singlePatient.size() != 1) {
            throw new RuntimeException("The patient must be the same");
        }

        if (hasOverlappingAppointments(medicalAppointments)) {
            throw new RuntimeException("There is overlapping appointments");
        }

        receiptService.addReceipt(medicalAppointments);
        return medicalAppointments;
    }

    private LocalDateTime calculateTime(MedicalAppointment ma) {
        return ma.getAppointmentStart().plus(ma.getMedicalService().getDuration());
    }

    private boolean hasOverlappingAppointments(List<MedicalAppointment> appointments) {
        return appointments.stream().anyMatch(a1 ->
                appointments.stream().anyMatch(a2 ->
                        !a1.equals(a2) && // Evitar compararse consigo misma
                                a1.getAppointmentStart().isBefore(a2.getAppointmentEnd()) &&
                                a1.getAppointmentEnd().isAfter(a2.getAppointmentStart())
                )
        );
    }

    @Override
    public MedicalAppointment updateMedicalAppointment(MedicalAppointment ma) {
        if (!medicalAppointmentRepository.existsById(ma.getId())) {
            throw new EntityNotFoundException("Medical appointment not found");
        }
        ma.setAppointmentEnd(calculateTime(ma));
        return medicalAppointmentRepository.save(ma);
    }

    @Override
    public MedicalAppointment getMedicalAppointmentById(long id) {
        return medicalAppointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical appointment not found"));
    }

    @Override
    public List<MedicalAppointment> getAllMedicalAppointmentsByDni(String dni) {
        return medicalAppointmentRepository.findAllByPatient_Dni(dni);
    }

    @Override
    public List<MedicalAppointment> getAllMedicalAppointments() {
        return medicalAppointmentRepository.findAll();
    }
}
