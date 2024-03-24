package com.invoice.invoicesystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private String invoiceNumber;
    private BigDecimal amount;
    private String customerEmail;
    private String customerPhoneNumber;
    private boolean paid;
}
