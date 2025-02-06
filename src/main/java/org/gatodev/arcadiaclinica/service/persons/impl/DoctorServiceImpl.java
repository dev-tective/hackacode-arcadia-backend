package org.gatodev.arcadiaclinica.service.persons.impl;

import jakarta.persistence.EntityNotFoundException;
import org.gatodev.arcadiaclinica.DTO.persons.DoctorDTO;
import org.gatodev.arcadiaclinica.DTO.persons.FullDoctorDTO;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.persons.IDoctorRepository;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorServiceImpl(IDoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(DoctorDTO doctorDTO) {
        return doctorRepository.save(modelMapper.map(doctorDTO, Doctor.class));
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
    public boolean existDoctor(String dni, String email) {
        return doctorRepository.existsByAttributes_DniOrAttributes_Email(dni, email);
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
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public FullDoctorDTO getFullDoctorDTO(Doctor doctor) {
        return modelMapper.map(doctor, FullDoctorDTO.class);
    }

    @Override
    public DoctorDTO getDoctorDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }
}
