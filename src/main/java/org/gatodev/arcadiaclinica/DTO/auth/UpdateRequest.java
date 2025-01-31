package org.gatodev.arcadiaclinica.DTO.auth;

import org.gatodev.arcadiaclinica.entity.IFieldsValidate;

public record UpdateRequest(
        String oldField,
        String newField
) implements IFieldsValidate {
}
