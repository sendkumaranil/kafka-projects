package com.myecommerce.shippingservice.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shipment {
    @Id
    private long orderId;

    @Embedded
    private Customer customer;

    private Date updated;

    @Embedded
    private Address shippingAddress =new Address();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShipmentLine> shipmentLine;

    public Shipment(){
        shipmentLine=new ArrayList<>();
    }

    public Shipment(long orderId,Customer customer, Date updated, Address shippingAddress, List<ShipmentLine> shipmentLine){
        this.orderId=orderId;
        this.customer=customer;
        this.updated=updated;
        this.shippingAddress = shippingAddress;
        this.shipmentLine=shipmentLine;
    }

    public Shipment(Customer customer){
        this.customer=customer;
        this.shipmentLine=new ArrayList<>();
    }

    public int getNumberOfLines() {
        return shipmentLine.size();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<ShipmentLine> getShipmentLine() {
        return shipmentLine;
    }

    public void setShipmentLine(List<ShipmentLine> shipmentLine) {
        this.shipmentLine = shipmentLine;
    }

    @Transient
    public void setOrderLine(List<ShipmentLine> orderLine) {
        this.shipmentLine = orderLine;
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
