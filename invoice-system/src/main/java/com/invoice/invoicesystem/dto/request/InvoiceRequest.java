package com.invoice.invoicesystem.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {

    private BigDecimal amount;
    private String customerEmail;
    private String customerPhoneNumber;

}
