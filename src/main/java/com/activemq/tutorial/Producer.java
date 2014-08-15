/**
 * 
 */
package com.activemq.tutorial;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author pramod.simikeri
 * 
 */
public class Producer {

	/**
	 * @param args
	 * @throws JMSException
	 */

	static String QUEUE_NAME = "TEST-QUEUE";
	static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				BROKER_URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(QUEUE_NAME);
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("Hello World!!");
		System.out.println("Sent message " + message.getText()
				+ " to the queue " + QUEUE_NAME);
		producer.send(message);
		connection.close();
	}
}
