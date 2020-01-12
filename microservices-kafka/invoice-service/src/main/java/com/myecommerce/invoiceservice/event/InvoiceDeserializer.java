package com.myecommerce.invoiceservice.event;

import com.myecommerce.invoiceservice.model.Invoice;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class InvoiceDeserializer extends JsonDeserializer<Invoice> {

    public InvoiceDeserializer(){
        super(Invoice.class);
    }
}
