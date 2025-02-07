package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.persons.IDoctorRepository;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        doctor.validateFields(doctor.getEmail(), doctor.getDni(), doctor.getNumberPhone());
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        if (doctor.getDni() == null || !doctorRepository.existsById(doctor.getId())) {
            throw new EntityNotFoundException("Doctor not found");
        }
        doctor.validateFields(doctor.getEmail(), doctor.getDni(), doctor.getNumberPhone());
        doctor.setEnabled(true);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    @Override
    public Doctor getDoctorByDni(String dni) {
        return doctorRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }

    @Override
    public void deactivateDoctor(long id) {
        Doctor doctor = getDoctorById(id);
        doctor.setEnabled(false);
        doctorRepository.save(doctor);
    }

    @Override
    public void activateDoctor(long id) {
        Doctor doctor = getDoctorById(id);
        doctor.setEnabled(true);
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
