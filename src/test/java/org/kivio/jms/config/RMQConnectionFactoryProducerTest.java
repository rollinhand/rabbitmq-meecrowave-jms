package org.kivio.jms.config;

import org.junit.jupiter.api.Test;

import javax.jms.ConnectionFactory;

import static org.junit.jupiter.api.Assertions.*;

class RMQConnectionFactoryProducerTest {
    @Test
    void it_should_build_a_connectionFactory() {
        RMQConnectionFactoryProducer factoryProducer = new RMQConnectionFactoryProducer();
        ConnectionFactory cf = factoryProducer.build();

        assertNotNull(cf);
    }
}