package ies.thiar;

import ies.thiar.Modelo.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainTwo {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("1234567890","Rafik","calle rafik","123456789","r2@gmal.com","1234");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(cliente);
        entityTransaction.commit();
    }
}
