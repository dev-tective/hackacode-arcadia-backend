package org.gatodev.arcadiaclinica.repository.persons;

import org.gatodev.arcadiaclinica.entity.medical.MedicalSpecialty;
import org.gatodev.arcadiaclinica.entity.persons.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByDni(String dni);

    List<Doctor> findAllByFirstname(String firstname);

    List<Doctor> findAllByLastname(String lastname);

    List<Doctor> findAllByFirstnameAndLastname(String firstname, String lastname);

    List<Doctor> findAllByMedicalSpecialty(MedicalSpecialty medicalSpecialty);

    List<Doctor> findAllByMedicalAppointmentsIsNull();

    List<Doctor> findAllByMedicalAppointmentsIsNotNull();

    List<Doctor> findAllByMedicalSpecialtyAndMedicalAppointmentsIsNull(MedicalSpecialty specialty);

    List<Doctor> findAllByMedicalSpecialtyAndMedicalAppointmentsIsNotNull(MedicalSpecialty specialty);
}
