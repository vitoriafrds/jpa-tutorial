package br.com.estudos.wrapper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerWrapper {

    public static final String PERSISTENCE_UNIT = "Clientes";

    public EntityManagerFactory getEntityManagerFactory() {
     return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }
}
