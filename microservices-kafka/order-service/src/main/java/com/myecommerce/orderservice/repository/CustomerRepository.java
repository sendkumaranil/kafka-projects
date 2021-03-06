package com.myecommerce.orderservice.repository;

import com.myecommerce.orderservice.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    List<Customer> findByFirstname(String firstname);
}
