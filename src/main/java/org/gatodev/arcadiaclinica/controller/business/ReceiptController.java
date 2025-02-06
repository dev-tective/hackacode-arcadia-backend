package org.gatodev.arcadiaclinica.controller.business;

import jakarta.validation.Valid;
import org.gatodev.arcadiaclinica.DTO.business.ReceiptRequest;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import org.gatodev.arcadiaclinica.service.business.IReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final IReceiptService receiptService;

    public ReceiptController(IReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PutMapping
    public ResponseEntity<Receipt> update(@Valid @RequestBody ReceiptRequest receiptRequest) {
        return ResponseEntity.ok(receiptService.updateReceipt(receiptRequest.receipt()));
    }

    @GetMapping
    public ResponseEntity<List<Receipt>> getAll() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Receipt> getById(@PathVariable long id) {
        return ResponseEntity.ok(receiptService.getReceiptById(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<List<Receipt>> getByDni(@PathVariable String dni) {
        return ResponseEntity.ok(receiptService.getAllReceiptsByDni(dni));
    }
}
