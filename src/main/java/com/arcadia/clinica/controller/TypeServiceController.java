package com.arcadia.clinica.controller;

import com.arcadia.clinica.entity.business_services.Specialty;
import com.arcadia.clinica.entity.business_services.TypeService;
import com.arcadia.clinica.response.ResponseMessage;
import com.arcadia.clinica.service.business_services.ITypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/type-service")
public class TypeServiceController {

    private final ITypeService typeService;
    public TypeServiceController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> createTypeServce(@RequestBody TypeService typeService) {
        this.typeService.saveType(typeService);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Type Service created successfully"));
    }

    @GetMapping("/name")
    public ResponseEntity<Optional<TypeService>> getTypeServiceByName(@RequestParam String name) {
        Optional<TypeService> typeServiceOptional = this.typeService.getTypeByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(typeServiceOptional);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<TypeService>> getAllTypeService() {
        return ResponseEntity.status(HttpStatus.OK).body(this.typeService.getAllTypes());
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<ResponseMessage> deleteTypeServiceById(@RequestParam Integer id) {
        this.typeService.deleteType(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Type Service deleted successfully"));
    }

}


