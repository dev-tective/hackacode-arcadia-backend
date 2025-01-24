package com.arcadia.clinica.service.business_services;

import com.arcadia.clinica.entity.business_services.TypeService;

import java.util.List;
import java.util.Optional;

public interface ITypeService {

    TypeService saveType(TypeService typeService);
    List<TypeService> getAllTypes();
    Optional<TypeService> getTypeByName(String typeName);
    void deleteType(Integer id);
    TypeService updateType(TypeService typeService, Integer id);
}
