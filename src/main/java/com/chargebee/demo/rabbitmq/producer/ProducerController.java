package com.chargebee.demo.rabbitmq.producer;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private MessageChannel greet;

    public ProducerController(HelloBinding binding){
        this.greet = binding.greeting();
    }

    @GetMapping("/greet/{name}")
    public void publish(@PathVariable("name") final String name){
        String greeting = "Hello " + name;
        Message<String> message = MessageBuilder.withPayload(greeting).build();
        greet.send(message);
    }
}
