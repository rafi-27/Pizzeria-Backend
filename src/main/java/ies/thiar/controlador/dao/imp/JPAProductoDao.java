package ies.thiar.controlador.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
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
        entityManager.getTransaction().begin();
        List<Ingrediente> ingredientes = new ArrayList<>();
        if (producto instanceof Pizza pizza) {
            ingredientes = pizza.getListaIngredientesPizza();
        } else if (producto instanceof Pasta pasta) {
            ingredientes = pasta.getListaIngredientePasta();
        }
        List<Ingrediente> ingredientesConID = new ArrayList<>();
        for (Ingrediente i : ingredientes) {
            Ingrediente ingredienteConId = entityManager.createQuery(
                    "SELECT ing FROM Ingrediente ing WHERE ing.nombre = :nombre", Ingrediente.class)
                    .setParameter("nombre", i.getNombre())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (ingredienteConId == null) {
                entityManager.persist(i);
                ingredienteConId = i;
            }
            ingredienteConId.setListaAlergenos(i.getListaAlergenos());
            ingredientesConID.add(ingredienteConId);
        }
        if (producto instanceof Pizza pizza) {
            pizza.setListaIngredientesPizza(ingredientesConID);
        } else if (producto instanceof Pasta pasta) {
            pasta.setListaIngredientePasta(ingredientesConID);
        }
        entityManager.persist(producto);
        entityManager.getTransaction().commit();
        entityManager.close();
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> listaProductos = new ArrayList<>();

        try {
            listaProductos = entityManager.createQuery("SELECT c FROM Producto c", Producto.class).getResultList();
            for (Producto producto : listaProductos) {
                if (producto instanceof Pizza pizza) {
                    Hibernate.initialize(pizza.getListaIngredientesPizza());
                } else if (producto instanceof Pasta pasta) {
                    Hibernate.initialize(pasta.getListaIngredientePasta());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en findAll.");
        } finally {
            entityManager.close();
        }
        return listaProductos;
    }

    @Override
    public List<Ingrediente> findIngredientesProducto(int idProd) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findIngredientesProducto'");
    }

    @Override
    public List<String> listaAlergenosIngrediente(int idIngre) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<String> listaAlergenos = new ArrayList<>();

        try {
            listaAlergenos = entityManager
                    .createQuery("SELECT a.listaAlergenos FROM IngredienteAlergenos a WHERE a.ingredienteId = :idIngre",
                            String.class)
                    .setParameter("idIngre", idIngre)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en listaAlergenosIngrediente.");
        } finally {
            entityManager.close();
        }
        return listaAlergenos;
    }

    public Ingrediente findIngredienteByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Ingrediente ingrediente = new Ingrediente();
        try {
            ingrediente = entityManager
                    .createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :name", Ingrediente.class)
                    .setParameter("nombre", name).getSingleResult();
            Hibernate.initialize(ingrediente.getListaAlergenos());
            return ingrediente;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en findIngredienteByName.");
        } finally {
            entityManager.close();
        }
        return null;
    }

}