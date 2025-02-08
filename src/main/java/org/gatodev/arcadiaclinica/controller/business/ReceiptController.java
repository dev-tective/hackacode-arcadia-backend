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

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @PostMapping
    public ResponseEntity<Receipt> addReceipt(@Valid @RequestBody ReceiptRequest receipt) {
        return ResponseEntity.ok(receiptService.addReceipt(receipt));
    }
}
