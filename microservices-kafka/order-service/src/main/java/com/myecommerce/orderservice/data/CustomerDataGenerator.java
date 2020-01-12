package com.myecommerce.orderservice.data;

import com.myecommerce.orderservice.model.Customer;
import com.myecommerce.orderservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomerDataGenerator {

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void generateTestData(){
        Customer cust1=new Customer("Anil","Kumar","anil.kumar@gmail.com","+919916722457");
        Customer cust2=new Customer("Kanika","Kumari","kumari.kanika@gmail.com","+919716722457");
        customerRepository.save(cust1);
        customerRepository.save(cust2);
    }
}
