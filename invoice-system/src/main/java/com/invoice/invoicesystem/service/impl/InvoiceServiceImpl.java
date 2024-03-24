package com.invoice.invoicesystem.service.impl;

import com.invoice.invoicesystem.dto.response.BaseResponse;
import com.invoice.invoicesystem.dto.response.InvoiceResponse;
import com.invoice.invoicesystem.model.Invoice;
import com.invoice.invoicesystem.repository.InvoiceRepository;
import com.invoice.invoicesystem.service.InvoiceService;
import com.invoice.invoicesystem.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private AppUtil appUtil;

    @Override
    public BaseResponse generateInvoice(BigDecimal amount, String customerEmail, String customerPhoneNumber) {

        log.info("Generate invoice request: {}", "amount -> " + amount + ", customer email -> " +
                customerEmail + ", customer phone number -> " + customerPhoneNumber);
        String message;
        try {
            String invoiceNumber = appUtil.generateRandomString();
            Invoice invoice = Invoice.builder()
                    .invoiceNumber(invoiceNumber)
                    .amount(amount)
                    .paid(false)
                    .customerEmail(customerEmail)
                    .customerPhoneNumber(customerPhoneNumber)
                    .build();
            invoiceRepository.save(invoice);
            InvoiceResponse dto =  mapToDTO(invoice);

            message = "Invoice successfully generated";
            return new BaseResponse<>(true,message,dto);

        } catch(Exception ex) {
            log.info("Generate invoice error: {} ", ex.fillInStackTrace());
            message = "Internal server error. Kindly contact the administrator";
            return new BaseResponse<>(false,message,null);

        }
    }

    @Override
    public BaseResponse payInvoice(String invoiceNumber) {

        log.info("Pay invoice request: {}", "invoice number -> " + invoiceNumber);
        String message;
        try {
            Invoice invoice = invoiceRepository.findByInvoiceNumberIgnoreCase(invoiceNumber);
            if(invoice == null) {
                message = "Invoice does not exist";
                return new BaseResponse<>(false,message,null);
            }
            invoice.setPaid(true);
            invoiceRepository.save(invoice);

            InvoiceResponse dto =  mapToDTO(invoice);

            message = "Invoice found.";
            return new BaseResponse<>(true,message,dto);

        } catch(Exception ex) {

            log.info("Pay invoice error: {} ", ex.fillInStackTrace());
            message = "Internal server error. Kindly contact the administrator";
            return new BaseResponse<>(false,message,null);
        }

    }

    private InvoiceResponse mapToDTO(Invoice invoice) {
        return InvoiceResponse.builder()
                .invoiceNumber(invoice.getInvoiceNumber())
                .amount(invoice.getAmount())
                .paid(invoice.isPaid())
                .customerEmail(invoice.getCustomerEmail())
                .customerPhoneNumber(invoice.getCustomerPhoneNumber())
                .build();
    }

    @Override
    public BaseResponse getAllInvoices(int page, int size) {
        log.info("Get all invoices......");
        String message;
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Invoice> invoicesPage = invoiceRepository.findAll(pageRequest);
            if(invoicesPage.isEmpty()) {
                message = "No record(s) found";
                return new BaseResponse<>(false,message,null);
            }
            Page<InvoiceResponse> dto = invoicesPage.map(this::mapToDTO);

            message = "Successful";
            return new BaseResponse<>(true,message,dto);

        } catch(Exception ex) {
            log.info("Get all invoices error: {} ", ex.fillInStackTrace());
            message = "Internal server error. Kindly contact the administrator";
            return new BaseResponse<>(false,message,null);
        }
    }

    @Override
    public BaseResponse getInvoicesByCustomer(String customerIdentifier, int page, int size) {
        log.info("Get invoices by customer......");
        String message;
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Invoice> invoicesPage = invoiceRepository.findByCustomerEmailOrCustomerPhoneNumber(customerIdentifier, customerIdentifier, pageRequest);
            if(invoicesPage.isEmpty()) {
                message = "No record(s) found";
                return new BaseResponse<>(false,message,null);
            }
            Page<InvoiceResponse> dto = invoicesPage.map(this::mapToDTO);

            message = "Invoice details successfully found";
            return new BaseResponse<>(true,message,dto);

        }catch(Exception ex) {
            log.info("Get all invoices by customer error: {} ", ex.fillInStackTrace());
            message = "Internal server error. Kindly contact the administrator";
            return new BaseResponse<>(false,message,null);
        }
    }

}


