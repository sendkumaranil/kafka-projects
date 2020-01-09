package com.myecommerce.orderservice.config;

import com.myecommerce.orderservice.model.Customer;
import com.myecommerce.orderservice.model.Item;
import com.myecommerce.orderservice.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringRestDataConfig extends RepositoryRestConfigurerAdapter {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Order.class, Item.class, Customer.class);
            }
        };
    }
}
