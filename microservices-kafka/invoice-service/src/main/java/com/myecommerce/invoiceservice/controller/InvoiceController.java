package com.myecommerce.invoiceservice.controller;

import com.myecommerce.invoiceservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView item(@PathVariable("id") long id){
        return new ModelAndView("invoice","invoice",invoiceRepository.findById(id).get());
    }

    @RequestMapping("/")
    public ModelAndView itemList(){
        return new ModelAndView("invoicelist","invoices",invoiceRepository.findAll());
    }
}
