package ies.thiar.controlador.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import ies.thiar.Modelo.Cliente;
import ies.thiar.controlador.dao.ClienteDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAClienteDao implements ClienteDao{

    private final EntityManagerFactory entityManagerFactory;

    public JPAClienteDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }


    @Override
    public void insert(Cliente client) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if (findByID(id) == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }else {
            Cliente client = findByID(id);
            client = entityManager.merge(client);
            entityManager.remove(client);
            entityManager.getTransaction().commit();
            entityManager.close();
        }       
    }

    @Override
    public void update(Cliente client) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Cliente findByID(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = entityManager.find(Cliente.class, id);

        if (cliente != null) {
            Hibernate.initialize(cliente.getListaPedidos());
        }
        entityManager.close();
        return cliente;
    }

    @Override
    public Cliente findByEmail(String email) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = new Cliente();
        try {
            cliente = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class).setParameter("email", email).getSingleResult();
            Hibernate.initialize(cliente.getListaPedidos());
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en findByEmail.");
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            listaClientes = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            for (Cliente cliente : listaClientes) {
                Hibernate.initialize(cliente.getListaPedidos());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en findAll.");
        } finally {
            entityManager.close();
        }

        return listaClientes;
    }
    
}
