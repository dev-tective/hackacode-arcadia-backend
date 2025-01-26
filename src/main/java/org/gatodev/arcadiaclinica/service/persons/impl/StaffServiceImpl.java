package org.gatodev.arcadiaclinica.service.persons.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.gatodev.arcadiaclinica.entity.persons.Role;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.repository.persons.IStaffRepository;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IRoleService;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements IStaffService, IPersonService<Staff> {

    private final IStaffRepository staffRepository;
    private final IUserService userService;
    private final IRoleService roleService;

    public StaffServiceImpl(
            IStaffRepository staffRepository,
            IUserService userService,
            IRoleService roleService
    ) {
        this.staffRepository = staffRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public Staff addEntity(Staff person) {
        verificatePerson(person);
        userService.encryptPassword(person);

        List<Role> roles = Arrays.asList(roleService.getRoleByName("ROLE_USER"),
                        roleService.getRoleByName("ROLE_STAFF"));

        if (person.getIsAdmin()) roles.add(roleService.getRoleByName("ROLE_ADMIN"));

        person.setRoles(roles);
        return staffRepository.save(person);
    }

    @Override
    public Staff updateEntity(Staff person) {
        if (!staffRepository.existsById(person.getId())) {
            throw new RuntimeException("Staff with id " + person.getId() + " does not exist");
        }
        verificatePerson(person);
        userService.encryptPassword(person);
        return staffRepository.save(person);
    }

    @Override
    public Staff getEntityByDni(String dni) {
        return staffRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No staff found with dni " + dni));
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
}
