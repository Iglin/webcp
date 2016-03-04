package com.kinopoisk.dao;

import com.kinopoisk.model.Country;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by user on 04.03.2016.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    //.configure("/home/alexander/repository/webcp/Kinopoisk/src/resources/hibernate.cfg.xml")
                    .configure()
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(Country.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
          //  System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}