package com.kinopoisk.dao;

import com.kinopoisk.model.*;
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
                    .configure()
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Director.class)
                    .addAnnotatedClass(Actor.class)
                    .addAnnotatedClass(Movie.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}