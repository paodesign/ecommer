package com.example.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
  
    private int id;
    private String createdOn;
    private String invoiceType;
    private int number;
    private String orderStatus;
    private List<OrderDetailDto> details;

    public OrderDto() {
        details = new ArrayList<OrderDetailDto>();
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setDetails(List<OrderDetailDto> details) {
        this.details = details;
    }
    public int getId() {
        return id;
    }
    public String getCreatedOn() {
        return createdOn;
    }
    public String getInvoiceType() {
        return invoiceType;
    }
    public int getNumber() {
        return number;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public List<OrderDetailDto> getDetails() {
        return details;
    }
}
