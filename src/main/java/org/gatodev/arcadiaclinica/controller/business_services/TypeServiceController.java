package org.gatodev.arcadiaclinica.controller.business_services;


import org.gatodev.arcadiaclinica.service.medical_services.IMedicalTypeServiceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-services")
public class TypeServiceController {

    private final IMedicalTypeServiceService typeServiceService;

    public TypeServiceController(IMedicalTypeServiceService typeServiceService) {
        this.typeServiceService = typeServiceService;
    }
    
}
