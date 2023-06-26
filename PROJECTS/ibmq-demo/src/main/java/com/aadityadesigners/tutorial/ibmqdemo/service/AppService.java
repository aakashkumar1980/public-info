package com.aadityadesigners.tutorial.ibmqdemo.service;

import org.springframework.stereotype.Service;

@Service
public class AppService {

    // TODO: Testing purpose
    public static Boolean _TEST_SHOULD_FAIL = Boolean.FALSE;
    public Boolean testSampleTask() {
        System.out.println("[AppService] testSampleTask()");

        if(_TEST_SHOULD_FAIL) throw new RuntimeException("TechnicalException occured.");
        return Boolean.TRUE;
    }
}
