package com.myecommerce.invoiceservice.service;

import com.myecommerce.invoiceservice.model.Invoice;
import com.myecommerce.invoiceservice.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public  void generateInvoice(Invoice invoice){
        if(invoiceRepository.existsById(invoice.getOrderId())){
            log.info("Invoice id already exists: "+invoice.getOrderId());
        }else{
            invoiceRepository.save(invoice);
        }
    }
}
