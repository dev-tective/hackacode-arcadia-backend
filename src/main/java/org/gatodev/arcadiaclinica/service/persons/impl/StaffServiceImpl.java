package org.gatodev.arcadiaclinica.service.persons.impl;

import java.util.List;
import java.util.UUID;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.repository.persons.IStaffRepository;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.gatodev.arcadiaclinica.util.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements IStaffService, IPersonService<Staff> {

    private final IStaffRepository staffRepository;

    public StaffServiceImpl(IStaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff addEntity(Staff person) {
        verificatePerson(person);
        IUserService.encryptPassword(person);
        return staffRepository.save(person);
    }

    @Override
    public Staff updateEntity(Staff person) {
        if (!staffRepository.existsById(person.getId())) {
            throw new RuntimeException("Staff with id " + person.getId() + " does not exist");
        }
        verificatePerson(person);
        IUserService.encryptPassword(person);
        return staffRepository.save(person);
    }

    @Override
    public Staff getEntityByDni(String dni) {
        return staffRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No staff found with dni " + dni));
    }

    @Override
    public Staff getEntityByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No staff found with email " + email));
    }

    @Override
    public Staff getEntityById(UUID id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No staff found with id " + id));
    }

    @Override
    public List<Staff> getAllEntities() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> getStaffByRole(Role role) {
        return getAllEntities()
                .stream()
                .filter(staff -> role.equals(staff.getRole()))
                .toList();
    }
}
