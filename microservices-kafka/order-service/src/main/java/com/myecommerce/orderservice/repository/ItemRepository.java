package com.myecommerce.orderservice.repository;

import com.myecommerce.orderservice.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ItemRepository extends PagingAndSortingRepository<Item,Long> {

    List<Item> findByItemname(@Param("itemname")String itemname);
    List<Item> findByItemnameContaining(@Param("itemname") String itemname);

    @Query("SELECT price FROM Item i WHERE i.itemId=?1")
    double price(long itemId);
}
