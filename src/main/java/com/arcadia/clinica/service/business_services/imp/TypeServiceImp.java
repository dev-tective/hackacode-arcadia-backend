package com.arcadia.clinica.service.business_services.imp;

import com.arcadia.clinica.entity.business_services.TypeService;
import com.arcadia.clinica.repository.business_services.ITypeServiceRepository;
import com.arcadia.clinica.service.business_services.ITypeService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Service
public class TypeServiceImp implements ITypeService {

    private final ITypeServiceRepository typeServiceRepository;
    public TypeServiceImp(ITypeServiceRepository typeServiceRepository) {
        this.typeServiceRepository = typeServiceRepository;
    }


    @Override
    public TypeService saveType(TypeService typeService) {
        return this.typeServiceRepository.save(typeService);
    }

    @Override
    public List<TypeService> getAllTypes() {
        return this.typeServiceRepository.findAll();
    }

    @Override
    public Optional<TypeService> getTypeByName(String typeName) {
        Optional<TypeService> typeService = this.typeServiceRepository.findTypeServiceByName(typeName);

        if(typeService.isPresent()){
            return typeService;
        }
        throw new RuntimeException("Tipo de servicio no encontrado");
    }

    @Override
    public void deleteType(Integer id) {
        this.typeServiceRepository.deleteById(id);
    }
}
