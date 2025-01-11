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
        entityManager.getTransaction().begin();
        entityManager.persist(producto);
        if (producto instanceof Pizza) {
            Pizza pizza = (Pizza) producto;
            Hibernate.initialize(pizza.getListaIngredientesPizza());
        }else if (producto instanceof Pasta) {
            Pasta pasta = (Pasta) producto;
            Hibernate.initialize(pasta.getListaIngredientePasta());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Producto producto) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Producto findByID(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
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
