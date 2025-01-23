package com.arcadia.clinica.repository.business_services;

import com.arcadia.clinica.entity.business_services.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeService extends JpaRepository<TypeService, Integer> {
}
