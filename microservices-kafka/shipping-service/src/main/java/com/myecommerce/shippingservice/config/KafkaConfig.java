package com.myecommerce.shippingservice.config;

import com.myecommerce.shippingservice.consumer.event.ShipmentDeserializer;
import com.myecommerce.shippingservice.model.Shipment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
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
public class KafkaConfig {

    @Value("${kafka.bootstrapservers}")
    private String bootstrapServers;

    @Value("${kafka.consumer.groupid}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, Shipment> consumerFactory(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ShipmentDeserializer.class);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        config.put(ShipmentDeserializer.TRUSTED_PACKAGES,"*");

        return new DefaultKafkaConsumerFactory<String,Shipment>(config,new StringDeserializer(),
                new JsonDeserializer<>(Shipment.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Shipment> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Shipment> factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
