package org.gatodev.arcadiaclinica.service.persons.impl;

import org.gatodev.arcadiaclinica.entity.persons.Role;
import org.gatodev.arcadiaclinica.repository.persons.IRoleRepository;
import org.gatodev.arcadiaclinica.service.persons.IRoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new RuntimeException("Role does not exist");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new RuntimeException("Role does not exist");
        }
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role does not exist"));
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role does not exist"));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
