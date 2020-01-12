package com.myecommerce.invoiceservice.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {

    @Id
    private long orderId;
    private Date updated;

    @Embedded
    private Customer customer;

    @Embedded
    private Address billingAddress=new Address();

    @OneToMany(cascade = CascadeType.ALL)
    private List<InvoiceLine> invoiceLine;

    public Invoice(){
        invoiceLine=new ArrayList<>();
    }
    public Invoice(long orderId,Customer customer,Address billingAddress, List<InvoiceLine> invoiceLine){
        this.orderId=orderId;
        this.customer=customer;
        this.billingAddress=billingAddress;
        this.invoiceLine=invoiceLine;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<InvoiceLine> getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(List<InvoiceLine> invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public int getNumberOfLines() {
        return invoiceLine.size();
    }

    @Transient
    public void setOrderLine(List<InvoiceLine> orderLine) {
        this.invoiceLine = orderLine;
    }

    public void addLine(int count, Item item) {
        this.invoiceLine.add(new InvoiceLine(count, item));
    }

    public double totalAmount(){
        return this.invoiceLine.stream()
                .map(ol -> ol.getCount() * ol.getItem().getPrice())
                .reduce(0.0,(d1,d2) -> d1 + d2);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this,o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
