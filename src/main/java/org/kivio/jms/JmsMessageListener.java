package org.kivio.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class JmsMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageListener.class);

    @Inject
    private ConnectionFactory connectionFactory;

    @Inject
    private Destination destination;

    private Connection connection;
    private Session session;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        LOG.info("Starting listener: {}", destination);

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);

            connection.start();
        } catch (JMSException e) {
            LOG.error("Cannot start JmsMessageListener");
        }
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        LOG.info("Consumer is destroyed - closing connection");
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(final Message message) {
        String response = null;
        try {
            if (message instanceof TextMessage) {
                LOG.info("Message is a TextMessage");
                response = convertMessage((TextMessage) message);
            } else if (message instanceof BytesMessage) {
                LOG.info("Message is a ByteMessage");
                response = convertMessage((BytesMessage) message);
            }
        } catch (JMSException e) {
            LOG.error("Cannot convert message", e);
        } finally {
            LOG.info("Received message:\n{}", response);
        }
    }

    private String convertMessage(TextMessage message) throws JMSException {
        return message.getText();
    }

    private String convertMessage(BytesMessage message) throws JMSException {
        byte[] byteData = new byte[(int) message.getBodyLength()];
        message.readBytes(byteData);
        String msg =  new String(byteData);
        message.reset();
        return msg;
    }
}
