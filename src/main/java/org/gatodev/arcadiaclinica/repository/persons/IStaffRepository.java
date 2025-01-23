package org.gatodev.arcadiaclinica.repository.persons;

import java.util.UUID;

import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<Staff, UUID> {

}
