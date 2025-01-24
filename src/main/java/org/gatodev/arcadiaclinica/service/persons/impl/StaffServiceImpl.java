package org.gatodev.arcadiaclinica.service.persons.impl;

import java.time.LocalTime;
import java.util.List;
import org.gatodev.arcadiaclinica.entity.persons.Staff;
import org.gatodev.arcadiaclinica.repository.persons.IStaffRepository;
import org.gatodev.arcadiaclinica.service.persons.IPersonService;
import org.gatodev.arcadiaclinica.service.persons.IStaffService;
import org.gatodev.arcadiaclinica.service.persons.IUserService;
import org.gatodev.arcadiaclinica.util.enums.DayOff;
import org.gatodev.arcadiaclinica.util.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements IStaffService, IPersonService<Staff> {

    private final IStaffRepository staffRepository;
    private final IUserService userService;

    public StaffServiceImpl(IStaffRepository staffRepository, IUserService userService) {
        this.staffRepository = staffRepository;
        this.userService = userService;
    }

    @Override
    public Staff addStaff(Staff staff) {
        checkSaveEntity(staff);
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        Staff oldStaff = getStaffByDni(staff.getDni());
        checkUpdatePerson(oldStaff, staff);
        return staffRepository.save(staff);
    }

    @Override
    public Staff getStaffByDni(String dni) {
        return staffRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No staff found with dni " + dni));
    }

    @Override
    public Staff getStaffByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No staff found with email " + email));
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> getStaffByRole(Role role) {
        return getAllStaff()
                .stream()
                .filter(staff -> role.equals(staff.getRole()))
                .toList();
    }

    // Revisa campos vacíos y existencias antes de guardar y actualizar
    @Override
    public void checkSaveEntity(Staff staff) {
        if (staff.getEntranceWork() == null || staff.getExitWork() == null) {
            throw new IllegalArgumentException("Entrance and exit work are required");
        }
        userService.checkSaveEntity(staff);
    }

    @Override
    public void checkUpdateEntity(Staff user, Staff updatedStaff) {
        userService.checkUpdateEntity(user, updatedStaff);
        final LocalTime entrance = updatedStaff.getEntranceWork();
        final LocalTime exit = updatedStaff.getExitWork();
        final DayOff dayOff = updatedStaff.getDayOff();

        if (entrance != null && entrance.equals(user.getEntranceWork())) {
            user.setEntranceWork(entrance);
        }

        if (exit != null && exit.equals(user.getExitWork())) {
            user.setExitWork(exit);
        }

        if (dayOff != null && dayOff.equals(user.getDayOff())) {
            user.setDayOff(dayOff);
        }
    }
}
