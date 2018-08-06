package com.infamous.notification;

import com.infamous.action.Action;
import javax.management.Notification;

/**
 * Created by infamouSs on 8/5/18.
 */
public class ActionNotification extends Notification {
    
    private static final long serialVersionUID = -1557161080542029197L;
    
    public ActionNotification(Action action, Object source, long sequenceNumber) {
        super(action.getId(), source, sequenceNumber, action.getMessage());
    }
    
    @Override
    public String toString() {
        return "Notification{" +
               "time=" + this.getTimeStamp() +
               ", type='" + this.getType() + '\'' +
               ", message='" + this.getMessage() + '\'' +
               '}';
    }
}
