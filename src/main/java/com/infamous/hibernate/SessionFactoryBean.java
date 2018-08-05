package com.infamous.hibernate;

import org.hibernate.SessionFactory;

public interface SessionFactoryBean {
    
    SessionFactory getSessionFactory();
}
