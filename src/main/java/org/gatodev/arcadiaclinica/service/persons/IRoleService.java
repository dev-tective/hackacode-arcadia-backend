package org.gatodev.arcadiaclinica.service.persons;

import org.gatodev.arcadiaclinica.entity.persons.Role;
import java.util.List;

public interface IRoleService {
    Role addRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Role role);

    Role getRoleById(int id);

    Role getRoleByName(String roleName);

    List<Role> getAllRoles();
}
