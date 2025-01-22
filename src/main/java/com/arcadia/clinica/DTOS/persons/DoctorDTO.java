package com.arcadia.clinica.DTOS.persons;

import com.arcadia.clinica.entity.business.AppointmentItem;
import com.arcadia.clinica.entity.business_services.Specialty;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO extends UserDTO {
    private Specialty specialty;
//    private List<AppointmentItemDTO> appointmentItemsDTO;
}
