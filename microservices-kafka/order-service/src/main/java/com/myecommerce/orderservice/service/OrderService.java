package com.myecommerce.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myecommerce.orderservice.model.Order;
import com.myecommerce.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String,Order> kafkaTemplate;

    public Order order(Order order) {
        if(order.getNumberOfLines()==0){
            throw new IllegalArgumentException("No Order lines");
        }
        order.setUpdated(new Date());
        Order result=orderRepository.save(order);
        fireOrderCreatedEvent(order);
        return result;
    }

    private void fireOrderCreatedEvent(Order order){
        System.out.println("Sending Order Details to the Topic::order");
        ObjectMapper mapper = new ObjectMapper();
        String msg=null;

        try{
            msg = mapper.writeValueAsString(order);
        }catch(Exception e){}

        System.out.println("Sending Message: "+msg);
        kafkaTemplate.send("order",order.getOrderId()+ " created ",order);
    }

    public double getPrice(long orderId) {
        return orderRepository.findById(orderId).get().totalPrice();
    }
}
