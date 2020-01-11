package com.myecommerce.shippingservice.controller;

import com.myecommerce.shippingservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView item(@PathVariable("id") long id){
        return new ModelAndView("shipment","shipment",shipmentRepository.findById(id).get());
    }

    @RequestMapping("/")
    public ModelAndView itemList(){
        return new ModelAndView("shipmentlist","shipments",shipmentRepository.findAll());
    }
}
