package org.gatodev.arcadiaclinica.util;

public interface CheckEntityPersistence {
    default boolean verificateStringField(String field) {
        return field == null || field.isEmpty();
    }
}
