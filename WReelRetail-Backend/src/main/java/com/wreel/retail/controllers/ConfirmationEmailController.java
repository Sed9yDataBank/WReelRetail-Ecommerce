package com.wreel.retail.controllers;

import com.wreel.retail.models.OrderMain;
import com.wreel.retail.services.SendGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirm")
public class ConfirmationEmailController {

    @Autowired
    SendGridService sendGridService;

    @PostMapping(value = "/email")
    public String index(@RequestBody OrderMain email) {
        String response = sendGridService.sendMail(email);
        return response;
    }
}
