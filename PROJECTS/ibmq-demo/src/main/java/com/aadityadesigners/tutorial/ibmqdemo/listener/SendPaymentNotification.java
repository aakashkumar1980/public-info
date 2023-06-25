package com.aadityadesigners.tutorial.ibmqdemo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class SendPaymentNotification {
    // TODO : Testing for IBM MQ retry
    private static int failureAndSuccessCount= 0, failureAll= 0, MAX_FAILURE_COUNT = 3;

    @JmsListener(destination = "EWS.QUEUE.SENDPAYMENT", containerFactory = "jmsFactory")
    public void receiveMessage(Message message) {
        System.out.println("Received <" + message + ">");


        // TODO : Testing for IBM MQ retry
        if(message.getMessage().contains("FAILURE_AND_SUCCESS")
            && ((failureAndSuccessCount++)<MAX_FAILURE_COUNT)) {
            System.err.println("[failureAndSuccessCount:"+failureAndSuccessCount+"] Technical Exception Occured.");

            try {Thread.currentThread().sleep(3000);} catch (InterruptedException e) {}
            throw new RuntimeException("Technical Exception Occured");

        } else if(message.getMessage().contains("FAILURE_ALL")) {
            System.err.println("[failureAll:"+(failureAll++)+"] Technical Exception Occured.");
            throw new RuntimeException("Technical Exception Occured");
        }
        
    }
}
