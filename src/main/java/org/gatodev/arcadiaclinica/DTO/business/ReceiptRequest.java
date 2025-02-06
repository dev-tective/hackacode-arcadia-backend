package org.gatodev.arcadiaclinica.DTO.business;

import org.gatodev.arcadiaclinica.entity.business.Receipt;

public record ReceiptRequest(
        Receipt receipt,
        long idMA
) {
}
