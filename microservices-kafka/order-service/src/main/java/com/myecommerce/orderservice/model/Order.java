package com.myecommerce.orderservice.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ORDERDETAILS")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Customer customer;

    private Date updated;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name="SHIPPING_STREET")),
            @AttributeOverride(name="zip",column = @Column(name="SHIPPING_ZIP")),
            @AttributeOverride(name="city",column = @Column(name="SHIPPING_CITY"))
    })
    private Address shippingAddress=new Address();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",column = @Column(name="BILLING_STREET")),
            @AttributeOverride(name="zip",column = @Column(name = "BILLING_ZIP")),
            @AttributeOverride(name="city",column = @Column(name = "BILLING_CITY"))
    })
    private Address billingAddress=new Address();

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderLine;

    public Order() {
        super();
        orderLine = new ArrayList<OrderLine>();
        updated();
    }

    public Order(Customer customer) {
        super();
        this.customer = customer;
        this.orderLine = new ArrayList<OrderLine>();
    }

    public void addLine(int count, Item item) {
        this.orderLine.add(new OrderLine(count, item));
        updated();
    }

    public int getNumberOfLines() {
        return orderLine.size();
    }

    public double totalPrice(){
            return orderLine.stream()
                    .map(ol -> ol.getCount() * ol.getItem().getPrice())
                    .reduce(0.0,(d1,d2) -> d1+d2);
    }

    public void updated(){
        updated=new Date();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        updated();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = new Date();
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        updated();
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        updated();
        this.billingAddress = billingAddress;
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

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine) {
        updated();
        this.orderLine = orderLine;
    }
}
