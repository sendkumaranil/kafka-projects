package com.myecommerce.invoiceservice.repository;

import com.myecommerce.invoiceservice.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice,Long> {

    @Query("SELECT max(i.updated) FROM Invoice i")
    Date lastUpdate();
}
