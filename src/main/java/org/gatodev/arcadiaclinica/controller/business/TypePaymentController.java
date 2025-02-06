package org.gatodev.arcadiaclinica.controller.business;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.entity.business.TypePayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/type-payment")
public class TypePaymentController {
    private final ICategoryService<TypePayment> typePaymentService;

    public TypePaymentController(
            @Qualifier("typePayment")
            ICategoryService<TypePayment> typePaymentService
    ) {
        this.typePaymentService = typePaymentService;
    }

    @GetMapping
    public ResponseEntity<List<TypePayment>> getAll() {
        return ResponseEntity.ok(typePaymentService.getAllCategory());
    }

    @PostMapping
    public ResponseEntity<TypePayment> create(@Valid @RequestBody TypePayment typePayment) {
        return ResponseEntity.ok(typePaymentService.addCategory(typePayment));
    }

    @PutMapping
    public ResponseEntity<TypePayment> update(@Valid @RequestBody TypePayment tp) {
        return ResponseEntity.ok(typePaymentService.updateCategory(tp));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<TypePayment> activate(@PathVariable long id) {
        typePaymentService.activateCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<TypePayment> deactivate(@PathVariable long id) {
        typePaymentService.deactivateCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
