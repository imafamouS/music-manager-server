package com.infamous.jms;

/**
 * Created by infamouSs on 8/6/18.
 */
public class TypeMessage {
    
    public static final String TEXT = "TEXT";
    
    public static final String BINARY = "BINARY";
    
    public static final String OBJECT = "OBJECT";
    
    
    public static String getType(String type) {
        switch (type) {
            case TEXT:
                return TEXT;
            case BINARY:
                return BINARY;
            case OBJECT:
            default:
                return OBJECT;
        }
    }
}
