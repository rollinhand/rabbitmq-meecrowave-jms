# JMS Messaging with RabbitMQ

This is an example project on how to use _RabbitMQ_ together with _Meecrowave_
and JMS.

**NOTE**: Jakarta namespace cannot be used because RabbitMQ has dependency
to javax, so we cannot use Meecrowaves jakarta extension so far.

## Preparing RabbitMQ

Usage of this project requires a running RabbitMQ instance.
Easiest way is to start a docker container:

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

After RabbitMQ is running, connect to the running container with a shell and
enter the following commands to enable JMS messaging:

```
rabbitmq-plugins enable rabbitmq_jms_topic_exchange
```

JMS is now enabled and the needed exchange and queue for this project can be
declared:

```
rabbitmqadmin declare queue --vhost=/ name=jms.demo.queue durable=true
rabbitmqadmin declare exchange --vhost=/ name=jms.demo.exchange durable=true type=direct
rabbitmqadmin declare binding --vhost=/ source=jms.demo.exchange destination=jms.demo.queue routing_key=jms.demo.routingKey 
```

## Starting Meecrowave

Meecrowave with its JMS messaging endpoint and JMS listener can be started with

```
mvn clean verify meecrowave:run
```

A message can be send with curl

```
curl -XPOST http://localhost:8080/jms -d '{"message":"test"}' -H 'Accept: application/json' -H 'Content-Type: application/json'
```

and a message should be displayed on the command line.
