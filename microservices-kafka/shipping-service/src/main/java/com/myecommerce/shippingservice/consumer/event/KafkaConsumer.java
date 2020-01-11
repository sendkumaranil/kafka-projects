package com.myecommerce.shippingservice.consumer.event;

import com.myecommerce.shippingservice.model.Shipment;
import com.myecommerce.shippingservice.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ShipmentService shipmentService;

    @KafkaListener(topics = "order")
    public void order(Shipment shipment, Acknowledgment acknowledgment){
        log.info("Received Shipment: "+shipment.getOrderId());
        shipmentService.ship(shipment);
        acknowledgment.acknowledge();
    }
}
