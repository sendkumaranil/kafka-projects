package com.myecommerce.shippingservice.repository;

import com.myecommerce.shippingservice.model.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface ShipmentRepository extends PagingAndSortingRepository<Shipment,Long> {

    @Query("SELECT max(s.updated) FROM Shipment s")
    Date lastUpdate();
}
