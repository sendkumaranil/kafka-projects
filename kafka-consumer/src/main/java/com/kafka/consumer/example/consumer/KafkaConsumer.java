package com.kafka.consumer.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.NetworkClient;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="#{'${kafka.consumer.topics}'.split(',')}",
            groupId = "${kafka.consumer.groupId}")
    public void consume(ConsumerRecord<String,String> record){
        System.out.println("============= KAFKA MESSAGE DETAILS =========");
        System.out.println("Key: "+record.key());
        System.out.println("Topic: "+record.topic());
        System.out.println("Offset: "+record.offset());
        System.out.println("Timestamp: "+record.timestamp());
        System.out.println("Consumed Message: "+record.value());
        System.out.println("==============================================");
    }
}
