package com.myecommerce.invoiceservice.config;

import com.myecommerce.invoiceservice.event.InvoiceDeserializer;
import com.myecommerce.invoiceservice.model.Invoice;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrapservers}")
    private String bootStrapServers;

    @Value("${kafka.consumer.groupid}")
    private String groupId;

    @Bean
    public ConsumerFactory<String,Invoice> consumerFactory(){
        Map<String, Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InvoiceDeserializer.class);
        config.put(InvoiceDeserializer.TRUSTED_PACKAGES,"*");
        return new DefaultKafkaConsumerFactory<String,Invoice>(config,
                new StringDeserializer(),new JsonDeserializer<>(Invoice.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Invoice> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Invoice> factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
