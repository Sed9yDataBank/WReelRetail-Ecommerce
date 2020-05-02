package com.wreel.retail.services;

import com.wreel.retail.models.OrderMain;

public interface SendGridService {

    public String sendMail(OrderMain emailPojo);
}
