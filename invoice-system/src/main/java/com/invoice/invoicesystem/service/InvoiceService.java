package com.invoice.invoicesystem.service;

import com.invoice.invoicesystem.dto.response.BaseResponse;

import java.math.BigDecimal;

public interface InvoiceService {

    BaseResponse generateInvoice(BigDecimal amount, String customerEmail, String customerPhoneNumber);
    BaseResponse payInvoice(String invoiceId);

    BaseResponse getAllInvoices(int page, int size);

    BaseResponse getInvoicesByCustomer(String customerIdentifier, int page, int size);
}
