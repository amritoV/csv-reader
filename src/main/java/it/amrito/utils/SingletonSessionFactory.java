package it.amrito.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingletonSessionFactory {

    private static SessionFactory factory;

    private SingletonSessionFactory(){

    }

    public static SessionFactory getInstance(){
        if(factory == null)
            factory = new Configuration().configure().buildSessionFactory();

        return factory;
    }
}
