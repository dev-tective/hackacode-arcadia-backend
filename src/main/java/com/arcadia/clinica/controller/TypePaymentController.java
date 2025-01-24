package com.arcadia.clinica.controller;

import com.arcadia.clinica.entity.business.TypePayment;
import com.arcadia.clinica.repository.business.ITypePaymentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-payment")
public class TypePaymentController {
    private final ITypePaymentRepository typePaymentRepository;

    public TypePaymentController(ITypePaymentRepository typePaymentRepository) {
        this.typePaymentRepository = typePaymentRepository;
    }

    @GetMapping("/add")
    public TypePayment getTypePayment(@RequestBody TypePayment typePayment) {
        TypePayment newTypePayment = new TypePayment();
        newTypePayment.setName(typePayment.getName());
        return typePaymentRepository.save(newTypePayment);
    }
}
