//package com.harshproject;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class RabbitMQConfigTest {
//
//    @Autowired
//    private Queue queue;
//
//    @Autowired
//    private DirectExchange directExchange;
//
//    @Autowired
//    private Binding binding;
//
//    @Test
//    void testQueueBean() {
//        assertNotNull(queue);
//    }
//
//    @Test
//    void testDirectExchangeBean() {
//        assertNotNull(directExchange);
//    }
//
//    @Test
//    void testBindingBean() {
//        assertNotNull(binding);
//    }
//}
