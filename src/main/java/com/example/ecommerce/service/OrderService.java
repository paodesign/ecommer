package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.OrderRepository;

public class OrderService {
    
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;


    public OrderService(OrderRepository orderRepository, BasketRepository basketRepository) {

        this.orderRepository = orderRepository;
        this.basketRepository = basketRepository;
    }

    public OrderDto checkOut(int basketId){
        Basket basket = basketRepository.findById(basketId).get();
        return null;
    }
}
