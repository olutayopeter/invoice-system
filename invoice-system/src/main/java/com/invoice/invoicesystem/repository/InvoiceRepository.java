package com.invoice.invoicesystem.repository;

import com.invoice.invoicesystem.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByInvoiceNumberIgnoreCase(String invoiceNumber);

    Page<Invoice> findByCustomerEmailOrCustomerPhoneNumber(String customerIdentifier, String customerIdentifier1, PageRequest pageRequest);
}
