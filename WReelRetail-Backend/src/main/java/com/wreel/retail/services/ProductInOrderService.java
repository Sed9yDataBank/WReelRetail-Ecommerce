package com.wreel.retail.services;


import com.wreel.retail.models.ProductInOrder;
import com.wreel.retail.models.User;

public interface ProductInOrderService {

    void update(String itemId, Integer quantity, User user);

    ProductInOrder findOne(String itemId, User user);
}
