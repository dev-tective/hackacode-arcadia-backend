package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import java.util.List;

public interface IDoctorService extends IPersonService<Doctor> {
    List<Doctor> getAllDoctorsByFirstname(String Firstname);

    List<Doctor> getAllDoctorsByLastname(String Lastname);

    List<Doctor> getAllDoctorsByFirstnameAndLastname(String Firstname, String Lastname);

    List<Doctor> getAllDoctorsBySpecialty(Integer idSpecialty);

    List<Doctor> getAllDoctorsByMedicalAppointmentIsNull();
    List<Doctor> getAllDoctorsByMedicalAppointmentIsNotNull();

    List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNull(Integer idSpecialty);
    List<Doctor> getAllDoctorsBySpecialtyAndMedicalAppointmentIsNotNull(Integer idSpecialty);
}
