package org.gatodev.arcadiaclinica.controller.business;

import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.gatodev.arcadiaclinica.service.business.ITypePaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/type-payment")
public class TypePaymentController {

    private final ITypePaymentService typePaymentService;

    public TypePaymentController(ITypePaymentService typePaymentService) {
        this.typePaymentService = typePaymentService;
    }

    @GetMapping
    public ResponseEntity<List<TypePayment>> getAllTypePayments() {
        return ResponseEntity.ok(typePaymentService.getAllTypePayments());
    }

    @GetMapping("{id}")
    public ResponseEntity<TypePayment> getTypePaymentById(@PathVariable long id) {
        return ResponseEntity.ok(typePaymentService.getTypePaymentById(id));
    }

    @PostMapping
    public ResponseEntity<TypePayment> addTypePayment(@RequestBody TypePayment typePayment) {
        return ResponseEntity.ok(typePaymentService.addTypePayment(typePayment));
    }

    @PutMapping
    public ResponseEntity<TypePayment> updateTypePayment(@RequestBody TypePayment typePayment) {
        return ResponseEntity.ok(typePaymentService.updateTypePayment(typePayment));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateTypePayment(@PathVariable long id) {
        typePaymentService.deactivateMedicalService(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<?> activateTypePayment(@PathVariable long id) {
        typePaymentService.activateMedicalService(id);
        return ResponseEntity.noContent().build();
    }
}
