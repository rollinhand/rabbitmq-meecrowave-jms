package org.kivio.jms.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JmsMessageTest {
    @Test
    void it_should_set_message() {
        final String expectedText = "Hello, world!";

        JmsMessage underTest = new JmsMessage();
        underTest.setMessage(expectedText);

        assertThat(underTest.getMessage()).isEqualTo(expectedText);
    }
}