package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypeServiceRepository extends JpaRepository<TypeService, Integer> {
    Optional<TypeService> findTypeServiceByName(String typeName);

}
