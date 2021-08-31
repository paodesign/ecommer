package com.example.ecommerce.entity;

import java.time.LocalDate;
import javax.persistence.*;

@Entity(name = "Purchases")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_on")
    private LocalDate createdOn;
    private InvoiceType invoiceType;
    //@Column(unique = true)
    private int number;
    private OrderStatus orderStatus;

    private int basket_id;

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }


    
}
