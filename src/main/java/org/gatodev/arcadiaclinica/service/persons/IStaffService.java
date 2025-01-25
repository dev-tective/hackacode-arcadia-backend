package org.gatodev.arcadiaclinica.service.persons;

import java.util.List;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.util.enums.Role;

public interface IStaffService extends IPersonService<Staff> {

    List<Staff> getStaffByRole(Role role);
}
