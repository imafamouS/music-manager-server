package com.infamous.notification;

import com.infamous.logging.Log;
import javax.management.AttributeChangeNotification;
import javax.management.Notification;

/**
 * Created by infamouSs on 8/5/18.
 */
public class ConsoleJMXNotification implements JMXNotification {
    
    
    private static final long serialVersionUID = 170597271527265027L;
    
    @Override
    public void handleNotification(Notification notification, Object handback) {
        Log.i(notification.toString());
        Log.i(handback);
    }
    
    @Override
    public boolean isNotificationEnabled(Notification notification) {
        return AttributeChangeNotification.class.isAssignableFrom(notification.getClass());
    }
    
    
}
