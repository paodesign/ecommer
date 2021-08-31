package com.example.ecommerce.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity(name = "Baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDate creadLocalDate;
    private BasketStatus status;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketDetail> details;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Basket() {
        this.status = BasketStatus.INPROGRESS;
        this.creadLocalDate = LocalDate.now();
        if(this.details == null || this.details.size() == 0){
            this.details = new ArrayList<BasketDetail>();
        }
    }

    public Basket(User user) {
        this();
        this.user = user;
    }

    public Basket(User user, BasketDetail detail) {
        this(user);
        this.details.add(detail);
    }

    public void setStatus(BasketStatus status) {
        this.status = status;
    }

    public LocalDate getCreadLocalDate() {
        return creadLocalDate;
    }

    public BasketStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<BasketDetail> getDetails() {
        return details;
    }

    public Boolean existProduct(Product product) {
        for (BasketDetail det : details) {

            if (product.equals(det.getProduct())) {
                return true;
            }
        }
        return false;
    }

    public Boolean existProduct(int id) {
        for (BasketDetail basketDetail : details) {

            if(basketDetail.getProduct().getId() == id){
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product) {
        if (existProduct(product)) {
            for (BasketDetail basketDetail : details) {

                if (product.getId() == basketDetail.getProduct().getId()) {
                    basketDetail.addAmountToOne();
                    return;
                }
            }
        }

        BasketDetail newBasketDetail = new BasketDetail(product, 1);
        newBasketDetail.setBasket(this);
        details.add(newBasketDetail);
    }

    public void deleteProduct(int prodId){
        for (int i = 0; i < details.size(); i++) {
            BasketDetail detail = details.get(i);
            if(detail.getProduct().getId() == prodId){
                if(detail.getAmount() > 1){
                    detail.subsAmountToOne();
                }
                else{
                    details.remove(i);
                }
                return;
            }
        }
    
    }

}