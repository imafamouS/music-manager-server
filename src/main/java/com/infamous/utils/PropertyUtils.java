package com.infamous.utils;

import com.infamous.logging.Log;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by infamouSs on 8/4/18.
 */
public class PropertyUtils {
    
    public static Properties getProperties(String path) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream(path));
        } catch (Exception ex) {
            Log.e(ex.getMessage(), ex);
        }
        
        return properties;
    }
}
