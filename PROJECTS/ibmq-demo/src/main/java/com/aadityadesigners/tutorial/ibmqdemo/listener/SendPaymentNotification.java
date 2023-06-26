package com.aadityadesigners.tutorial.ibmqdemo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.aadityadesigners.tutorial.ibmqdemo.service.AppService;


@Component
public class SendPaymentNotification {

    @Autowired AppService appService;

    @JmsListener(destination = "EWS.QUEUE.SENDPAYMENT", containerFactory = "jmsFactory")
    public void receiveMessage(Message message) {
        System.out.println("[SendPaymentNotification] receiveMessage(message: "+message+")");
        
        // TODO: Testing
        appService.testSampleTask();
        
    }
}
