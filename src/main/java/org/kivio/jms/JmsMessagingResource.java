package org.kivio.jms;

import org.kivio.jms.domain.JmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("jms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JmsMessagingResource {
    private static final Logger LOG = LoggerFactory.getLogger(JmsMessagingResource.class);

    @Inject
    private ConnectionFactory connectionFactory;

    @Inject
    private Destination destination;

    @POST
    public Response sendMessage(JmsMessage msg) throws JMSException {
        LOG.info("Sending message: '{}'", msg.getMessage());

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(destination);
        TextMessage txtMessage = session.createTextMessage(msg.getMessage());
        txtMessage.setStringProperty("JmsType", "TextMessage");

        connection.start();
        producer.send(txtMessage);

        producer.close();
        session.close();
        connection.close();

        return Response.status(Response.Status.OK).build();
    }
}
