package com.example.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.BasketDetail;

public class BasketDto {
    public int id;
    public int products = 0;
    public String status;
    public List<BasketItemDto> items = new ArrayList<BasketItemDto>();

    public void mapFromEntity(Basket b){
        this.id = b.getId();
        this.status = b.getStatus().name();

        for (BasketDetail bitem : b.getDetails()) {
            products += bitem.getAmount();
            var temp = new BasketItemDto();
            temp.amount = bitem.getAmount();
            temp.product.mapFromEntity(bitem.getProduct());
            temp.total = bitem.getTotal();
            this.items.add(temp);
        }
    }
}