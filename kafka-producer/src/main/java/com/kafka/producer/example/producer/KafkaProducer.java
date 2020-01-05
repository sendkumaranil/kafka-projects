package com.kafka.producer.example.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka-producer")
public class KafkaProducer {

    //@Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Value("${kafka.producer.topics}")
    private String topics;

    public KafkaProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String msg = mapper.writeValueAsString(message);
            kafkaTemplate.send(topics,msg);
        }catch(Exception e){
            //handle exception here
            return "Error Occurred:"+e.getMessage();
        }
        return "Message Sent To Kafka Topics: "+topics;
    }

}
