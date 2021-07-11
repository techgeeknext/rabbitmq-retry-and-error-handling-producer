package com.techgeeknext.controller;

import com.techgeeknext.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RabbitMQProducerController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/sendMessage")
    public String producer(@RequestParam("name") String name, @RequestParam("domain") String domain,
						   @RequestParam("exp") int exp) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setDomain(domain);
        emp.setExperience(exp);

        //The convertAndSend method converts the java object to an amqp message,
        // and then sends this message via the routing key to the exchange.
        amqpTemplate.convertAndSend("techgeeknextExchange", "techgeeknext", emp);
        return "Message sent to RabbitMQ server successfully!!";
    }
}
