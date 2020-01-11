package com.myecommerce.shippingservice.consumer.event;

import com.myecommerce.shippingservice.model.Shipment;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ShipmentDeserializer extends JsonDeserializer<Shipment> {

    public ShipmentDeserializer(){
        super(Shipment.class);
    }
}
