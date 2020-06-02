package com.wreel.retail.services;

import com.wreel.retail.models.Cart;
import com.wreel.retail.models.ProductInOrder;
import com.wreel.retail.models.User;

import java.util.Collection;

public interface CartService {

    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
