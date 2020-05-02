package com.wreel.retail.services.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.wreel.retail.models.OrderMain;
import com.wreel.retail.models.ProductInOrder;
import com.wreel.retail.services.SendGridService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class SendGridServiceImpl implements SendGridService {

    @Value("${sendgrid.api-key.secret}")
    private String sendGridApi;

    private Mail PersonalizeEmail(OrderMain emailPojo) {

        Mail mail = new Mail();

        /* From information setting */
        Email fromEmail = new Email();
        fromEmail.setName("WReelRetail");
        fromEmail.setEmail("wrrservice@wreelretail.com");

        mail.setFrom(fromEmail);
        mail.setSubject("WReelRetail Order Payment Confirmation And Details");

        Personalization personalization = new Personalization();
        Email to = new Email();
        to.setName(emailPojo.getBuyerName());
        to.setEmail(emailPojo.getBuyerEmail());
        personalization.addTo(to);

        personalization.addHeader("X-Test", "test");
        personalization.addHeader("X-Mock", "true");

        /* Substitution value settings */
        personalization.addSubstitution("%name%", "WReelRetail");
        personalization.addSubstitution("%from%", "wrrservice@wreelretail.com");

        mail.addPersonalization(personalization);

        /* Set template id */
        mail.setTemplateId("Your_SendGrid_Template_Id");

        /* Reply to setting */
        Email replyTo = new Email();
        replyTo.setName("WReelRetail");
        replyTo.setEmail("wrrservice@wreelretail.com");
        mail.setReplyTo(replyTo);

        /* Adding Content of the email */
        Content content = new Content();

        ProductInOrder productInOrder = new ProductInOrder();
        BigDecimal amount = productInOrder.getProductPrice();
        /* Adding email message/body */
        content.setType("text/plain");
        content.setValue("Hello " + emailPojo.getBuyerName() + " Your WReelRetail Order Is Confirmed, " +
                "Here's What You Ordered" +
                emailPojo.getProducts() +
                " Your Order Total Price Including Taxes In USD Is " + amount +
                " Your Order Got Created From Your Personal Account On " + emailPojo.getCreateTime() +
                "And Last Updated On " + emailPojo.getUpdateTime() +
                "This Email Is An Order Confirmation Email For Security Reasons Since You Used Our Online Integrated Online Payment" +
                " For Further Information About Your Order, Please Login In To Your WReelRetail Account...");
        mail.addContent(content);

        return mail;
    }

    @Override
    public String sendMail(OrderMain emailPojo) {

        SendGrid sg = new SendGrid(sendGridApi);
        sg.addRequestHeader("X-Mock", "true");

        Request request = new Request();
        Mail mail = PersonalizeEmail(emailPojo);
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Failed To Send Mail! " + ex.getMessage();
        }
        return "Email Has Been Sent Successfully!!";
    }
}


