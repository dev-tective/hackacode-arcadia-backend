package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import java.util.List;

public interface IDoctorService {
    Doctor addDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    Doctor getDoctorById(long id);

    Doctor getDoctorByDni(String dni);

    void deactivateDoctor(long id);

    void activateDoctor(long id);

    List<Doctor> getAllDoctors();
}
