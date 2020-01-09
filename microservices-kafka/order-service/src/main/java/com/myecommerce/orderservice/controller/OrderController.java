package com.myecommerce.orderservice.controller;

import com.myecommerce.orderservice.model.Customer;
import com.myecommerce.orderservice.model.Item;
import com.myecommerce.orderservice.model.Order;
import com.myecommerce.orderservice.repository.CustomerRepository;
import com.myecommerce.orderservice.repository.ItemRepository;
import com.myecommerce.orderservice.repository.OrderRepository;
import com.myecommerce.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    @ModelAttribute("items")
    public Iterable<Item> items(){
        return itemRepository.findAll();
    }

    @ModelAttribute("customers")
    public Iterable<Customer> customers() {
        return customerRepository.findAll();
    }

    @RequestMapping("/")
    public ModelAndView orderList(){
        return new ModelAndView("orderlist","orders",orderRepository.findAll());
    }

    @RequestMapping(value = "/form.html",method = RequestMethod.GET)
    public ModelAndView form(){
        return new ModelAndView("orderForm","order",new Order());
    }

    @RequestMapping(value = "/line", method = RequestMethod.POST)
    public ModelAndView addLine(Order order) {
        order.addLine(0, itemRepository.findAll().iterator().next());
        return new ModelAndView("orderForm", "order", order);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("id") long id) {
        return new ModelAndView("order", "order", orderRepository.findById(id).get());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView post(Order order) {
        order = orderService.order(order);
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ModelAndView post(@PathVariable("id") long id) {
        orderRepository.deleteById(id);

        return new ModelAndView("success");
    }
}
