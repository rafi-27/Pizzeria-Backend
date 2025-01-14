package ies.thiar.controlador.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;

import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.ProductoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAProductoDao implements ProductoDao {
    private final EntityManagerFactory entityManagerFactory;

    public JPAProductoDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }
    @Override
    public void insert(Producto producto) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
    
            if (producto instanceof Pizza) {
                Pizza pizza = (Pizza) producto;
                for (Ingrediente ingrediente : pizza.getListaIngredientesPizza()) {
                    if (ingrediente.getId() != 0) {
                        entityManager.merge(ingrediente);
                    } else {
                        entityManager.persist(ingrediente);
                    }
                }
            } else if (producto instanceof Pasta) {
                Pasta pasta = (Pasta) producto;
                for (Ingrediente ingrediente : pasta.getListaIngredientePasta()) {
                    if (ingrediente.getId() != 0) {
                        entityManager.merge(ingrediente);
                    } else {
                        entityManager.persist(ingrediente);
                    }
                }
            }
    
            entityManager.persist(producto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new SQLException("Error persisting Producto", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (findByID(id) == null) {
            throw new IllegalArgumentException("No se ha podido encontrar el producto.");
        } else {
            Producto producto = findByID(id);
            producto = entityManager.merge(producto);
            entityManager.remove(producto);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public void update(Producto producto) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(producto);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Producto findByID(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Producto producto = entityManager.find(Producto.class, id);
        try {
            if (producto != null) {
                if (producto instanceof Pizza) {
                    Hibernate.initialize(((Pizza) producto).getListaIngredientesPizza());
                } else if (producto instanceof Pasta) {
                    Hibernate.initialize(((Pasta) producto).getListaIngredientePasta());
                }
                return producto;
            } else {
                return null;
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Ingrediente> findIngredientesProducto(int idProd) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIngredientesProducto'");
    }

    @Override
    public List<String> listaAlergenosIngrediente(int idIngre) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listaAlergenosIngrediente'");
    }

}