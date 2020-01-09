package com.myecommerce.orderservice.formatter;

import com.myecommerce.orderservice.model.Customer;
import com.myecommerce.orderservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CustomerFormatter implements Formatter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer parse(String s, Locale locale) throws ParseException {
        return customerRepository.findById(Long.parseLong(s)).get();
    }

    @Override
    public String print(Customer customer, Locale locale) {
        return customer.getCustomerId().toString();
    }
}
