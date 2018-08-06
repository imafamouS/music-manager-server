package com.infamous.jms.sender;

import com.infamous.jms.TypeMessage;
import com.infamous.logging.Log;
import java.io.Serializable;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Created by infamouSs on 8/6/18.
 */
public class Sender implements ISender {
    
    private JmsTemplate jmsTemplate;
    
    public Sender() {
    
    }
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    public void send(String type, Object object) {
        switch (type) {
            case TypeMessage.TEXT:
                send((String) object);
                break;
            case TypeMessage.BINARY:
                send((Byte[]) object);
                break;
            case TypeMessage.OBJECT:
                if (object instanceof Serializable) {
                    send((Serializable) object);
                } else {
                    Log.i("Object must be Serializable");
                }
                break;
        }
    }
    
    @Override
    public void send(final String message) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                Log.i("Sending text message:" + message);
                
                return textMessage;
            }
        });
    }
    
    @Override
    public void send(final byte[] binary) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bytesMessage = session.createBytesMessage();
                bytesMessage.writeBytes(binary);
                Log.i("Sending binary message");
                
                return bytesMessage;
            }
        });
    }
    
    @Override
    public void send(final Serializable object) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(object);
                Log.i("Sending object message");
                
                return objectMessage;
            }
        });
    }
}
