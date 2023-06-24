package com.aadityadesigners.tutorial.ibmqdemo.listener;

import java.util.UUID;

import lombok.Data;

@Data
public class Message {
    private String coorelationId = UUID.randomUUID().toString();
    private String message;
}
