package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.gatodev.arcadiaclinica.repository.persons.IDoctorRepository;
import org.gatodev.arcadiaclinica.service.persons.IDoctorService;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService, IPersonService<Doctor> {

    private final IStaffService staffService;
    private final IDoctorRepository doctorRepository;

    public DoctorServiceImpl(IStaffService staffService, IDoctorRepository doctorRepository) {
        this.staffService = staffService;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor getDoctorByDni(String dni) {
        return doctorRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
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

    // Revisa campos vacíos y existencias antes de guardar y actualizar
    @Override
    public void checkSaveEntity(Doctor person) {
        //Buscar la Especialidad
        staffService.checkSaveEntity(person);
    }

    @Override
    public void checkUpdateEntity(Doctor person, Doctor updatedPerson) {
        //Buscar la especialidad
        staffService.checkUpdateEntity(person, updatedPerson);
    }
}
