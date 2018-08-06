package com.infamous;

import com.infamous.jms.receiver.Receiver;
import com.infamous.jms.sender.Sender;
import com.infamous.logging.Log;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Server implements MusicManagerServer {
    
    private ApplicationContext context;
    
    public static void main(String[] args) throws Exception {
        
        Server app = new Server();
        app.start();
        
        ActiveMQQueue myTopic = (ActiveMQQueue) app.getContext().getBean("destination");
        JmsTemplate jmsTemplate = (JmsTemplate) app.getContext().getBean("jmsTemplateBean");
        
        Log.d(myTopic != null ? "TOPIC # null" : "TOPIC is null");
        Log.d(jmsTemplate != null ? "jmstemplate # null" : "jmstemplate is null");
        
        Receiver sender = (Receiver) app.getContext().getBean("messageReceiver");
        Sender sender1 = (Sender) app.getContext().getBean("messageSender");
        Log.d(sender != null ? "sender # null" : "sender is null");
        
        sender1.send("SEND NEW MESSAGE");
        //
        Log.d(sender.getJmsTemplate().receive());
    }
    
    public static void clientSendMessageJMS() throws JMSException {
        Connection connection = null;
        try {
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                      "tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                      Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("customerQueue");
            MessageProducer producer = session.createProducer(queue);
            String task = "Task";
            for (int i = 0; i < 10; i++) {
                String payload = task + i;
                Message msg = session.createTextMessage(payload);
                System.out.println("Sending text '" + payload + "'");
                producer.send(msg);
            }
            producer.send(session.createTextMessage("END"));
            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    @Override
    public void start() {
        try {
            context = new ClassPathXmlApplicationContext(MusicManagerServer.MODULE_PATH);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage(), ex);
        }
    }
    
    public ApplicationContext getContext() {
        return context;
    }
    
    /*
    private void clientTest()
              throws IOException, MalformedObjectNameException, IntrospectionException, InstanceNotFoundException, ReflectionException {
        Log.d("test");
        JMXServiceURL url = new JMXServiceURL(
                  "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/connector");
        JMXConnector cn = JMXConnectorFactory.connect(url);
        MBeanServerConnection msc = cn.getMBeanServerConnection();
        ObjectName helloObjName = new ObjectName("com.infamous:name=musicManagerService");
        MBeanInfo info = msc.getMBeanInfo(helloObjName);
        
        Log.d(info.toString());
    }*/
}
