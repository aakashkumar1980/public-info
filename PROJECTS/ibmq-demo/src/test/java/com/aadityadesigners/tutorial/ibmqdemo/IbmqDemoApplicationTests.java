package com.aadityadesigners.tutorial.ibmqdemo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.aadityadesigners.tutorial.ibmqdemo.listener.Message;
import com.aadityadesigners.tutorial.ibmqdemo.service.AppService;

@SpringBootTest
class IbmqDemoApplicationTests {

	@Autowired ApplicationContext applicationContext;

	@Test
	void mqSuccess() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

		System.out.println("[IbmqDemoApplicationTests] mqSuccess()");
		AppService._TEST_SHOULD_FAIL = Boolean.FALSE;
		Message message = new Message(); message.setMessage("This is a SUCCESS message."); 
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", message);
	}

	
	@Test
	void mqFailure() {
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

		System.out.println("[IbmqDemoApplicationTests] mqFailure()");
		AppService._TEST_SHOULD_FAIL = Boolean.TRUE;
		Message message = new Message(); message.setMessage("This is a FAILURE message."); 
		jmsTemplate.convertAndSend("EWS.QUEUE.SENDPAYMENT", message);
	}
	@Test
	void mqSuccessAfterFailure() {
		System.out.println("[IbmqDemoApplicationTests] mqSuccessAfterFailure()");
		AppService._TEST_SHOULD_FAIL = Boolean.FALSE;
	}	


}
