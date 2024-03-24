package com.invoice.invoicesystem.controller;

import com.invoice.invoicesystem.dto.response.BaseResponse;
import com.invoice.invoicesystem.dto.request.InvoiceRequest;
import com.invoice.invoicesystem.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/generate/invoice")
    public ResponseEntity generateInvoice(@RequestBody InvoiceRequest request) {
        log.info("Calling generate invoice service......");
        try {
            BaseResponse<?> response = invoiceService.generateInvoice(request.getAmount(), request.getCustomerEmail(), request.getCustomerPhoneNumber());
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            log.info("Error calling generate inovice service: {} ", ex.fillInStackTrace());
            String message = "Error generating invoice. Kindly contact the administrator";
            return ResponseEntity.ok(new BaseResponse<>(false,message,null));
        }
    }

    @PostMapping("/pay/invoice")
    public ResponseEntity payInvoice(@RequestParam String invoiceNumber) {
        log.info("Calling pay invoice service.....:");
        try {
            BaseResponse<?> response = invoiceService.payInvoice(invoiceNumber);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            log.info("Error calling pay invoice service: {} ", ex.fillInStackTrace());
            String message = "Error paying for  invoice. Kindly contact the administrator";
            return ResponseEntity.ok(new BaseResponse<>(false,message,null));
        }
    }

    @GetMapping("/view/all/generated/invoice")
    public ResponseEntity getAllInvoices(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        log.info("Calling view all generated invoice service......");
        try {
            BaseResponse<?> response = invoiceService.getAllInvoices(page, size);
            return ResponseEntity.ok(response);
        }catch(Exception ex) {
            log.info("Error calling view all generated invoice service: {} ", ex.fillInStackTrace());
            String message = "Error getting all generated invoice. Kindly contact the administrator";
            return ResponseEntity.ok(new BaseResponse<>(false,message,null));
        }
    }

    @GetMapping("/view/generated/invoice/by/customer/emailOrPhone")
    public ResponseEntity getInvoicesByCustomer(@RequestParam String customerIdentifier,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        log.info("Calling view generated invoice by customer service......");
        try {
            BaseResponse<?> response = invoiceService.getInvoicesByCustomer(customerIdentifier, page, size);
            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            log.info("Error calling view generated invoice by customer: {} ", ex.fillInStackTrace());
            String message = "Error getting all generated invoice by " + customerIdentifier +  ". Kindly contact the administrator";
            return ResponseEntity.ok(new BaseResponse<>(false,message,null));
        }
    }


}

