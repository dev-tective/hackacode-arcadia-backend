package com.arcadia.clinica.entity.persons;

import com.arcadia.clinica.entity.business_services.Specialty;
import com.arcadia.clinica.entity.business.AppointmentItem;
import com.arcadia.clinica.util.DayOff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor extends User {
    @ManyToOne
    private Specialty specialty;
    @OneToMany(targetEntity = AppointmentItem.class, mappedBy = "doctor")
    private List<AppointmentItem> appointmentItems;
}
