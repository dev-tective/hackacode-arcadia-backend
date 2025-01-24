package com.arcadia.clinica.entity.persons;

import com.arcadia.clinica.util.DayOff;
import com.arcadia.clinica.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalTime entranceWork;
    @Column(nullable = false)
    private LocalTime exitWork;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Float salary;
    @Enumerated(EnumType.STRING)
    private DayOff dayOff;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalTime getEntranceWork() {
        return entranceWork;
    }

    public void setEntranceWork(LocalTime entranceWork) {
        this.entranceWork = entranceWork;
    }

    public LocalTime getExitWork() {
        return exitWork;
    }

    public void setExitWork(LocalTime exitWork) {
        this.exitWork = exitWork;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public DayOff getDayOff() {
        return dayOff;
    }

    public void setDayOff(DayOff dayOff) {
        this.dayOff = dayOff;
    }
}