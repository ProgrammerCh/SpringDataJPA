package ch.practice.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }


}