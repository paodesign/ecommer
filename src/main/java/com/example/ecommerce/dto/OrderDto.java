package com.example.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
  
    public int id;
    public String createdOn;
    public String invoiceType;
    public int number;
    public String orderStatus;
    public List<OrderDetailDto> details;
}
