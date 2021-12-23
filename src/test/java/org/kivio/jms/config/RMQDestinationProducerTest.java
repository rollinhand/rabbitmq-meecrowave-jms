package org.kivio.jms.config;

import org.junit.jupiter.api.Test;

import javax.jms.Destination;

import static org.junit.jupiter.api.Assertions.*;

class RMQDestinationProducerTest {
    @Test
    void it_should_build_a_destination() {
        RMQDestinationProducer destinationProducer = new RMQDestinationProducer();
        Destination destination = destinationProducer.buildDestination();

        assertNotNull(destination);
    }
}