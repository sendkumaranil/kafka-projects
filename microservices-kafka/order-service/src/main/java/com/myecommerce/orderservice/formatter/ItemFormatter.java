package com.myecommerce.orderservice.formatter;

import com.myecommerce.orderservice.model.Item;
import com.myecommerce.orderservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ItemFormatter implements Formatter<Item> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item parse(String s, Locale locale) throws ParseException {
        return itemRepository.findById(Long.parseLong(s)).get();
    }

    @Override
    public String print(Item item, Locale locale) {
        return item.getItemId().toString();
    }
}
