package com.aadityadesigners.tutorial.ibmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.aadityadesigners.tutorial.ibmqdemo.listener.Message;

@SpringBootApplication
public class IbmqDemoApplication {

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(IbmqDemoApplication.class, args);

		/** TESTING **/
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending an message.");
		Message hello = new Message(); hello.setMessage("Hello, how are you?");
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", hello);
	}

}
