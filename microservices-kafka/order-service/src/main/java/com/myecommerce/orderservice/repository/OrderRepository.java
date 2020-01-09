package com.myecommerce.orderservice.repository;

import com.myecommerce.orderservice.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(collectionResourceRel = "order",path="order")
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {

    @Query("SELECT max(o.updated) FROM Order o")
    Date lastUpdate();
}
