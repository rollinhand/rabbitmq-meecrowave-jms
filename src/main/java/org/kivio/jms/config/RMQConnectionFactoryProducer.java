package org.kivio.jms.config;

import com.rabbitmq.jms.admin.RMQConnectionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;

@ApplicationScoped
public class RMQConnectionFactoryProducer {

    @Produces
    public ConnectionFactory build() {
        RMQConnectionFactory factory = new RMQConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
        return factory;
    }
}
