package com.aadityadesigners.tutorial.ibmqdemo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SendPaymentNotification {

    @JmsListener(destination = "EWS.QUEUE.SENDPAYMENT", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        System.out.println("Received <" + message + ">");
    }
}
