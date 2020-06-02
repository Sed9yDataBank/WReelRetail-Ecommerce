package com.wreel.retail.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.wreel.retail.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.key.secret}")
    private String key;

    @Override
    public Charge createCharge(int amount, StripeService.Currency currency, String token) throws StripeException {
        Stripe.apiKey = this.key;

        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);
        chargeMap.put("currency", currency.name());
        chargeMap.put("source", token);

        Charge charge = Charge.create(chargeMap);

        return charge;
    }

}
