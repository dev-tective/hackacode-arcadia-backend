package org.gatodev.arcadiaclinica.service.business.impl;

import org.gatodev.arcadiaclinica.DTO.business.MedicalAppointmentRequest;
import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import org.gatodev.arcadiaclinica.entity.persons.Client;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.business.IMedicalAppointmentRepository;
import org.gatodev.arcadiaclinica.service.business.IMedicalAppointmentService;
import org.gatodev.arcadiaclinica.service.medical.IMedicalServiceS;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicalAppointmentServiceImpl implements IMedicalAppointmentService {

    private final IDoctorService doctorService;
    private final IMedicalServiceS medicalServiceS;
    private final IMedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentServiceImpl(
            IMedicalAppointmentRepository appointmentRepository,
            IDoctorService doctorService,
            IMedicalServiceS medicalServiceS
    ) {
        this.medicalAppointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.medicalServiceS = medicalServiceS;
    }

    @Override
    public void addMedicalAppointment(MedicalAppointment medicalAppointment) {
        medicalAppointmentRepository.save(medicalAppointment);
    }

    @Override
    public MedicalAppointment createMedicalAppointment(MedicalAppointmentRequest medicalAppointment, Client patient) {
        if (!patient.getEnabled()) {
            throw new RuntimeException("Patient is not enabled");
        }

        Doctor doctor = doctorService.getDoctorById(medicalAppointment.idDoctor());
        if (!doctor.getEnabled()) {
            throw new RuntimeException("Doctor is not enabled");
        }


        MedicalService ms = medicalServiceS.getMedicalServiceById(medicalAppointment.idMedicalService());
        if (!ms.getState()) {
            throw new RuntimeException("Medical service is not active");
        }

        LocalDateTime appointmentEnd = medicalAppointment
                .appointmentStart().plus(ms.getDuration());
        if (existMedicalAppointmentByDoctor(
                doctor.getId(), appointmentEnd, medicalAppointment.appointmentStart())) {
            throw new RuntimeException("Doctor has another appointment on the same date and time.");
        }
        if (!doctorService.existsMedicalAppointmentByDoctor(
                doctor.getId(),
                medicalAppointment.appointmentStart().toLocalTime(),
                appointmentEnd.toLocalTime()
        )) {
            throw new RuntimeException("The doctor does not work during these hours");
        }

        MedicalAppointment ma = new MedicalAppointment();
        ma.setDoctor(doctor);
        ma.setPatient(patient);
        ma.setMedicalService(ms);
        ma.setAppointmentStart(medicalAppointment.appointmentStart());
        ma.setAppointmentEnd(appointmentEnd);
        return ma;
    }

    private boolean existMedicalAppointmentByDoctor(
            long idDoctor, LocalDateTime appointmentEnd, LocalDateTime appointmentStart
    ) {
        return medicalAppointmentRepository
                .existsByDoctor_IdAndAppointmentStartBeforeAndAppointmentEndAfter(
                idDoctor, appointmentEnd, appointmentStart
        );
    }
}
