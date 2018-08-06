package com.infamous.jms.receiver;

import com.infamous.logging.Log;
import javax.jms.Message;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by infamouSs on 8/6/18.
 */
public class Receiver implements IReceiver {
    
    private JmsTemplate jmsTemplate;
    
    public Receiver() {
    
    }
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    @Override
    public void receiveMessage(String type) {
        Message message = jmsTemplate.receive();
        
        Log.d(message.toString());
    }
}
