package com.infamous.hibernate;

import com.infamous.utils.PropertyUtils;
import java.util.Properties;
import org.hibernate.cfg.Configuration;

/**
 * Created by infamouSs on 8/4/18.
 */
public class HibernateUtils {
    
    
    public static Configuration createConfiguration(String configPath) {
        Configuration configuration = new Configuration();
        configuration.configure(configPath);
        
        return configuration;
    }
    
    public static Configuration createConfiguration(String configPath, String propertiesPath) {
        Configuration configuration = createConfiguration(configPath);
        configuration.setProperties(PropertyUtils.getProperties(propertiesPath));
        
        return configuration;
    }
    
    public static Configuration createConfiguration(String configPath, Properties properties) {
        Configuration configuration = createConfiguration(configPath);
        configuration.setProperties(properties);
        
        return configuration;
    }
    
}
