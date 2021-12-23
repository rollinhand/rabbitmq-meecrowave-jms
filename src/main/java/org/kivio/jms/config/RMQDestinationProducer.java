package org.kivio.jms.config;

import com.rabbitmq.jms.admin.RMQDestination;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.Destination;

@ApplicationScoped
public class RMQDestinationProducer {
    protected static final String DESTINATION_NAME = "jms.demo.queue";
    protected static final String AMQP_EXCHANGE_NAME = "jms.demo.exchange";
    protected static final String AMQP_ROUTING_KEY = "jms.demo.routingKey";

    @Produces
    public Destination buildDestination() {
        // Producer needs some more information to send a message
        RMQDestination rmqDestination = new RMQDestination();
        rmqDestination.setDestinationName(DESTINATION_NAME);
        rmqDestination.setAmqp(true);
        rmqDestination.setAmqpExchangeName(AMQP_EXCHANGE_NAME);
        rmqDestination.setAmqpRoutingKey(AMQP_ROUTING_KEY);
        rmqDestination.setAmqpQueueName(DESTINATION_NAME);
        return rmqDestination;
    }
}
