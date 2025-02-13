package com.wreel.retail.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface StripeService {

    public enum Currency {
        EUR, USD;
    }

    public Charge createCharge(int amount, StripeService.Currency currency, String token) throws StripeException;
}
