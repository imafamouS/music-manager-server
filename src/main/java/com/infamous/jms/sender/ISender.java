package com.infamous.jms.sender;

import java.io.Serializable;

/**
 * Created by infamouSs on 8/6/18.
 */
public interface ISender {
    
    void send(String message);
    
    void send(byte[] binary);
    
    void send(Serializable object);
}
