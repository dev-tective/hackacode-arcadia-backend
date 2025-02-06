package org.gatodev.arcadiaclinica.service.business;

import org.gatodev.arcadiaclinica.entity.business.MedicalAppointment;
import org.gatodev.arcadiaclinica.entity.business.Receipt;
import java.util.List;

public interface IReceiptService {
    Receipt addReceipt(List<MedicalAppointment> medicalAppointment);
    Receipt updateReceipt(Receipt receipt);
    Receipt getReceiptById(long id);
    List<Receipt> getAllReceipts();
    List<Receipt> getAllReceiptsByDni(String dni);
}
