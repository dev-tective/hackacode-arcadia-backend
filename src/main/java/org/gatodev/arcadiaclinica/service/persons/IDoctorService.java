package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import java.util.List;

public interface IDoctorService {
    Doctor addDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    Doctor getDoctorByDni(String dni);

    List<Doctor> getAllDoctors();

    List<Doctor> getAllDoctorsByFirstname(String Firstname);

    List<Doctor> getAllDoctorsByLastname(String Lastname);

    List<Doctor> getAllDoctorsByFirstnameAndLastname(String Firstname, String Lastname);

    List<Doctor> getAllDoctorsBySpecialty(Integer idSpecialty);

    List<Doctor> getAllDoctorsByMedicalAppointmentIsNull();
    List<Doctor> getAllDoctorsByMedicalAppointmentIsNotNull();

    List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNull(Integer idSpecialty);
    List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNotNull(Integer idSpecialty);
}
