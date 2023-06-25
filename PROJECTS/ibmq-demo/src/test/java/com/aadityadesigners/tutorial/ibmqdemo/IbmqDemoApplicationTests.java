package com.aadityadesigners.tutorial.ibmqdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.aadityadesigners.tutorial.ibmqdemo.listener.Message;

@SpringBootTest
class IbmqDemoApplicationTests {

	@Autowired ApplicationContext applicationContext;

	@Test
	void mqSuccess() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending a SUCCESS message.");
		Message hello = new Message(); 
		hello.setMessage("This is a SUCCESS message.");
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", hello);
		try {Thread.currentThread().sleep(15000);} catch (InterruptedException e) {}
	}


	@Test
	void mqFailureAndSuccess() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending a FAILURE_AND_SUCCESS message.");
		Message hello = new Message(); 
		hello.setMessage("This is a FAILURE_AND_SUCCESS message.");
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", hello);

		try {Thread.currentThread().sleep(15000);} catch (InterruptedException e) {}
	}
	@Test
	void mqFailureAll() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending a FAILURE_ALL message.");
		Message hello = new Message(); 
		hello.setMessage("This is a FAILURE_ALL message.");
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", hello);

		try {Thread.currentThread().sleep(15000);} catch (InterruptedException e) {}
	}
}
