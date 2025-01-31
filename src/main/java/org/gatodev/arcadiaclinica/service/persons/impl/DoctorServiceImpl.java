package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.persons.IDoctorRepository;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addEntity(Doctor person) {
        verificatePerson(person);
        return doctorRepository.save(person);
    }

    @Override
    public Doctor updateEntity(Doctor person) {
        if (!doctorRepository.existsById(person.getId())) {
            throw new EntityNotFoundException("Doctor not found");
        }
        verificatePerson(person);
        return doctorRepository.save(person);
    }

    @Override
    public Doctor getEntityByDni(String dni) {
        return doctorRepository.findByDni(dni)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with dni " + dni + " not found"));
    }

    @Override
    public Doctor getEntityById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void deactivateEntity(long id) {
        Doctor doctor = getEntityById(id);
        doctor.setEnabled(false);
        doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public void activateEntity(long id) {
        Doctor doctor = getEntityById(id);
        doctor.setEnabled(true);
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllEntities() {
        return doctorRepository.findAll();
    }
}
