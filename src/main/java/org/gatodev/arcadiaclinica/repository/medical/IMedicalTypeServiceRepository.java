package org.gatodev.arcadiaclinica.repository.medical;

import org.gatodev.arcadiaclinica.entity.medical.MedicalTypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalTypeServiceRepository extends JpaRepository<MedicalTypeService, Long> {
}
