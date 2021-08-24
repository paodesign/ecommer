package com.example.ecommerce.entity;

import java.time.LocalDate;
import javax.persistence.*;

@Entity(name = "purchase")
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


    
}
