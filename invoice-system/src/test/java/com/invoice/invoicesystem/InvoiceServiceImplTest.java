package com.invoice.invoicesystem;

import com.invoice.invoicesystem.dto.response.BaseResponse;
import com.invoice.invoicesystem.model.Invoice;
import com.invoice.invoicesystem.repository.InvoiceRepository;
import com.invoice.invoicesystem.service.InvoiceService;
import com.invoice.invoicesystem.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.math.BigDecimal;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InvoiceServiceImplTest {

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Test
    public void generateInvoice_Success() {

        // Test data
        BigDecimal amount = BigDecimal.valueOf(100);
        String customerEmail = "damilola@yahoo.com";
        String customerPhoneNumber = "08039224088";

        // Invoke service method
        BaseResponse response = invoiceService.generateInvoice(amount, customerEmail, customerPhoneNumber);

        // Assertions
        assertTrue(response.isFlag());
        assertEquals("Invoice successfully generated", response.getMessage());
        assertNotNull(response.getResult());
    }

    @Test
    public void payInvoice_InvoiceFound() {

        // Test data
        String invoiceNumber = "INV123456789";

        // Invoke service method
        BaseResponse response = invoiceService.payInvoice(invoiceNumber);

        // Assertions
        assertTrue(response.isFlag());
        assertEquals("Invoice found.", response.getMessage());
        assertNotNull(response.getResult());
    }

    @Test
    public void getAllInvoices_Success() {
        // Mocking repository behavior
        Page<Invoice> mockPage = new PageImpl<>(Collections.singletonList(new Invoice()));
        when(invoiceRepository.findAll(any(PageRequest.class))).thenReturn(mockPage);

        // Invoke service method
        BaseResponse response = invoiceService.getAllInvoices(0, 10);

        // Assertions
        assertTrue(response.isFlag());
        assertEquals("Successful", response.getMessage());
        assertNotNull(response.getResult());
        assertEquals(1, ((Page<?>) response.getResult()).getContent().size());
    }

    @Test
    public void getInvoicesByCustomer_Success() {
        // Mocking repository behavior
        Page<Invoice> mockPage = new PageImpl<>(Collections.singletonList(new Invoice()));
        when(invoiceRepository.findByCustomerEmailOrCustomerPhoneNumber(anyString(), anyString(), any(PageRequest.class))).thenReturn(mockPage);

        // Invoke service method
        BaseResponse response = invoiceService.getInvoicesByCustomer("customerIdentifier", 0, 10);

        // Assertions
        assertTrue(response.isFlag());
        assertEquals("Invoice details successfully found", response.getMessage());
        assertNotNull(response.getResult());
        assertEquals(1, ((Page<?>) response.getResult()).getContent().size());
    }
}
