package org.gatodev.arcadiaclinica.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseDTO {
    private Long id;
    private String name;
    private String code;
    private boolean state;
}
