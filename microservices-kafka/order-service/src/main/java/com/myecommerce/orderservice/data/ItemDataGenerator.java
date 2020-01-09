package com.myecommerce.orderservice.data;

import com.myecommerce.orderservice.model.Item;
import com.myecommerce.orderservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ItemDataGenerator {

    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void generateTestItemData(){
        Item item1=new Item("Lenevo Laptop",45000.00);
        Item item2=new Item("iPad",35000.00);
        Item item3=new Item("Apple iPhoneX",68000.00);
        Item item4=new Item("Boat Headphone",2500.00);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
    }
}
