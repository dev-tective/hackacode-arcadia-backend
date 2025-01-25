package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.persons.IDoctorRepository;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addEntity(Doctor person) {
        return null;
    }

    @Override
    public Doctor updateEntity(Doctor person) {
        return null;
    }

    @Override
    public Doctor getEntityByDni(String dni) {
        return null;
    }

    @Override
    public Doctor getEntityByEmail(String email) {
        return null;
    }

    @Override
    public Doctor getEntityById(UUID id) {
        return null;
    }

    @Override
    public List<Doctor> getAllEntities() {
        return List.of();
    }

    @Override
    public List<Doctor> getAllDoctorsByFirstname(String FirstName) {
        return doctorRepository.findAllByFirstname(FirstName);
    }

    @Override
    public List<Doctor> getAllDoctorsByLastname(String LastName) {
        return doctorRepository.findAllByLastname(LastName);
    }

    @Override
    public List<Doctor> getAllDoctorsByFirstnameAndLastname(String Firstname, String Lastname) {
        return doctorRepository.findAllByFirstnameAndLastname(Firstname, Lastname);
    }

    @Override
    public List<Doctor> getAllDoctorsByMedicalAppointmentIsNull() {
        return doctorRepository.findAllByMedicalAppointmentsIsNull();
    }

    @Override
    public List<Doctor> getAllDoctorsByMedicalAppointmentIsNotNull() {
        return doctorRepository.findAllByMedicalAppointmentsIsNotNull();
    }

    @Override
    public List<Doctor> getAllDoctorsBySpecialty(Integer idSpecialty) {
        return List.of();
    }

    @Override
    public List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNull(Integer idSpecialty) {
        return List.of();
    }

    @Override
    public List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNotNull(Integer idSpecialty) {
        return null;
    }
}
