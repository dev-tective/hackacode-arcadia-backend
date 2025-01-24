package org.gatodev.arcadiaclinica.service.persons;

import java.util.List;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.entity.persons.User;
import org.gatodev.arcadiaclinica.util.enums.Role;

public interface IStaffService {
    Staff addStaff(Staff staff);

    Staff updateStaff(Staff staff);

    Staff getStaffByDni(String dni);

    Staff getStaffByEmail(String email);

    List<Staff> getAllStaff();

    List<Staff> getStaffByRole(Role role);

    void checkSaveEntity(Staff person);

    void checkUpdateEntity(Staff person, Staff updatedPerson);
}
