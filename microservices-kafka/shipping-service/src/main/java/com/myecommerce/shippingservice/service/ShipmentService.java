package com.myecommerce.shippingservice.service;

import com.myecommerce.shippingservice.model.Shipment;
import com.myecommerce.shippingservice.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ShipmentService {

    private final Logger log = LoggerFactory.getLogger(ShipmentService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Transactional
    public void ship(Shipment shipment){
        if(shipmentRepository.existsById(shipment.getOrderId())){
            log.info("Shipment id {} already exists - ignored "+shipment.getOrderId());
        }else{
            shipmentRepository.save(shipment);
        }
    }
}
