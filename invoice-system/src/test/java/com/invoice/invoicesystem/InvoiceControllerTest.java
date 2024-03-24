package com.invoice.invoicesystem;

import com.invoice.invoicesystem.controller.InvoiceController;
import com.invoice.invoicesystem.dto.response.BaseResponse;
import com.invoice.invoicesystem.dto.response.InvoiceResponse;
import com.invoice.invoicesystem.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    public void generateInvoice_Success() throws Exception {
        // Mocking service response
        when(invoiceService.generateInvoice(any(BigDecimal.class), anyString(), anyString()))
                .thenReturn(new BaseResponse<>(true, "Invoice successfully generated", new InvoiceResponse()));

        // Test data
        String requestJson = "{\"amount\":100.00,\"customerEmail\":\"dayo@yahoo.com\",\"customerPhoneNumber\":\"08034186088\"}";

        // Perform the POST request
        mockMvc.perform(post("/api/invoices/generate/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.message").value("Invoice successfully generated"))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    public void payInvoice_Success() throws Exception {
        // Mocking service response
        when(invoiceService.payInvoice(anyString()))
                .thenReturn(new BaseResponse<>(true, "Invoice found.", new InvoiceResponse()));

        // Perform the POST request
        mockMvc.perform(post("/api/invoices/pay/invoice")
                        .param("invoiceNumber", "INV123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.message").value("Invoice found."))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    public void getAllInvoices_Success() throws Exception {
        // Mocking service response
        Page<InvoiceResponse> page = new PageImpl<>(Collections.singletonList(new InvoiceResponse()));
        when(invoiceService.getAllInvoices(anyInt(), anyInt()))
                .thenReturn(new BaseResponse<>(true, "Successful", page));

        // Perform the GET request
        mockMvc.perform(get("/api/invoices/view/all/generated/invoice")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.message").value("Successful"))
                .andExpect(jsonPath("$.result").exists());
    }

    @Test
    public void getInvoicesByCustomer_Success() throws Exception {
        // Mocking service response
        Page<InvoiceResponse> page = new PageImpl<>(Collections.singletonList(new InvoiceResponse()));
        when(invoiceService.getInvoicesByCustomer(anyString(), anyInt(), anyInt()))
                .thenReturn(new BaseResponse<>(true, "Invoice details successfully found", page));

        // Perform the GET request
        mockMvc.perform(get("/api/invoices/view/generated/invoice/by/customer/emailOrPhone")
                        .param("customerIdentifier", "customer@example.com")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.message").value("Invoice details successfully found"))
                .andExpect(jsonPath("$.result").exists());
    }
}
