package com.wreel.retail.controllers;

import com.stripe.exception.StripeException;
import com.wreel.retail.enums.PaymentEnum;
import com.wreel.retail.models.OrderMain;
import com.wreel.retail.models.ProductInOrder;
import com.wreel.retail.services.OrderService;
import com.wreel.retail.services.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    StripeService stripeService;

    @Autowired
    OrderService orderService;

    //Prototype...
    @PostMapping
    public ResponseEntity payOrder (@RequestHeader Map<String, String> headers, @PathVariable Long orderId) {

        ProductInOrder productInOrder = new ProductInOrder();
        BigDecimal amount = productInOrder.getProductPrice();
        String token = headers.get("token");

        try {
            stripeService.createCharge(amount.intValue(), StripeService.Currency.USD, token);
        } catch (StripeException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops, Our Servers Are Currently Experiencing Problems. Please Try Again Later!", e);
        }

        OrderMain order = orderService.findOne(orderId);
        order.setPaymentStatus(PaymentEnum.PAID_ONLINE.getCode());

        return ResponseEntity.ok(orderService.finish(orderId));
    }
}
