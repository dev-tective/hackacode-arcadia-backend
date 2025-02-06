package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.DTO.persons.DoctorDTO;
import org.gatodev.arcadiaclinica.DTO.persons.FullDoctorDTO;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import java.util.List;

public interface IDoctorService {
    Doctor addDoctor(Doctor doctor);

    Doctor updateDoctor(DoctorDTO doctor);

    Doctor getDoctorById(long id);

    Doctor getDoctorByDni(String dni);

    boolean existDoctor(String dni, String email);

    void deactivateDoctor(long id);

    void activateDoctor(long id);

    List<Doctor> getDoctors();

    FullDoctorDTO getFullDoctorDTO(Doctor doctor);

    DoctorDTO getDoctorDTO(Doctor doctor);
}
