package com.arcadia.clinica.service.business_services;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID, D> {
    T save(D entity);
    Optional<T> findById(ID idService);
    List<T> findAll();
    void delete(ID idService);
    T update(ID idService, D entity);
}
