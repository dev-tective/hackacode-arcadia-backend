package org.gatodev.arcadiaclinica.DTO.business;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gatodev.arcadiaclinica.entity.medical.MedicalService;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MedicalAppointmentDTO {
    private Long id;
    private LocalDateTime attentionDate;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private MedicalService medicalService;
}
