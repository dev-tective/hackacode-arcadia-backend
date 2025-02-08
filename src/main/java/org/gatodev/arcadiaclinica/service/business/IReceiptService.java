package org.gatodev.arcadiaclinica.service.business;

import org.gatodev.arcadiaclinica.DTO.business.ReceiptRequest;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import java.util.List;

public interface IReceiptService {
    Receipt addReceipt(ReceiptRequest request);
    List<Receipt> getAllReceipts();
}
