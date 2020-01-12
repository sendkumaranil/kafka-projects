package com.myecommerce.invoiceservice.event;

import com.myecommerce.invoiceservice.model.Invoice;
import com.myecommerce.invoiceservice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConsumer {

    private final Logger log = LoggerFactory.getLogger(InvoiceConsumer.class);

    @Autowired
    private InvoiceService invoiceService;

    @KafkaListener(topics = "order")
    public void invoice(Invoice invoice, Acknowledgment acknowledgment){
        log.info("Received Invoice :"+invoice);
        invoiceService.generateInvoice(invoice);
        acknowledgment.acknowledge();
    }
}
