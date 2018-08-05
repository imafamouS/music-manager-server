package com.infamous.hibernate;

import com.infamous.logging.Log;
import com.infamous.utils.PropertyUtils;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryBeanImpl implements SessionFactoryBean {
    
    private String configPath;
    private String propertiesPath;
    
    private SessionFactory sessionFactory;
    
    public SessionFactoryBeanImpl() {
    
    }
    
    @Override
    public synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (SessionFactoryBeanImpl.class) {
                sessionFactory = createFactory();
            }
        }
        return sessionFactory;
    }
    
    public String getConfigPath() {
        return configPath;
    }
    
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
    
    public String getPropertiesPath() {
        return propertiesPath;
    }
    
    public void setPropertiesPath(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }
    
    private SessionFactory createFactory() {
        SessionFactory sessionFactory = null;
        try {
            String propertyPath = ClassLoader
                      .getSystemResource(propertiesPath)
                      .getPath();
            Properties properties = PropertyUtils.getProperties(propertyPath);
            
            Configuration configuration = HibernateUtils
                      .createConfiguration(configPath, properties);
            
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                      .applySettings(configuration.getProperties())
                      .buildServiceRegistry();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage(), ex);
        }
        return sessionFactory;
    }
}
