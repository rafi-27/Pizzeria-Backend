package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import ies.thiar.Modelo.Cliente;
import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.Ingrediente;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pasta;
import ies.thiar.Modelo.Pedido;
import ies.thiar.Modelo.Pizza;
import ies.thiar.Modelo.Producto;
import ies.thiar.controlador.dao.PedidoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAPedidoDao implements PedidoDao {
    private final EntityManagerFactory entityManagerFactory;

    public JPAPedidoDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    @Override
    public void insert(Pedido pedido) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (pedido.getPago() != null && pedido.getPago().getId() == 0) {
            entityManager.persist(pedido.getPago());
        }

        for (LineaPedido lineaPedido : pedido.getLineaPedido()) {
            Producto producto = lineaPedido.getProduct();
            if (producto != null) {
                if (producto.getId() == 0) {
                    entityManager.persist(producto);
                } else {
                    entityManager.merge(producto);
                }
            }
        }

        Cliente cliente = pedido.getCliente();
        Cliente clientePersistido = entityManager.find(Cliente.class, cliente.getId());
        if (clientePersistido != null) {
            pedido.setCliente(clientePersistido);
        } else {
            entityManager.persist(cliente);
        }

        entityManager.persist(pedido);
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
            Pedido pedido = findByID(id);
            pedido = entityManager.merge(pedido);
            entityManager.remove(pedido);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public void update(Pedido pedido) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(pedido);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Pedido findByID(int id) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Pedido pedido = entityManager.find(Pedido.class, id);
        try {
            if (pedido != null) {
                Hibernate.initialize(pedido.getLineaPedido());
                return pedido;
            } else {
                return null;
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Pedido> listaPedidos = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new SQLException("El cliente es null.");
            } else {
                listaPedidos = cliente.getListaPedidos();
                listaPedidos.size();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                System.out.println("Error en obtenerPedidosByIdClient.");
            }
        } finally {
            entityManager.close();
        }
        return listaPedidos;
    }

    @Override
    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Pedido> listaPedidosByState = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            listaPedidosByState = entityManager
                    .createQuery("SELECT c FROM Pedido c WHERE c.estado = :state", Pedido.class)
                    .setParameter("state", state.name())
                    .getResultList();

            for (Pedido pedido : listaPedidosByState) {
                Hibernate.initialize(pedido.getLineaPedido());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                System.out.println("Error en obtenerPedidosByState.");
            }
        } finally {
            entityManager.close();
        }
        return listaPedidosByState;
    }

    @Override
    public List<LineaPedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<LineaPedido> listaLineaPedidos = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new SQLException("El Pedido es null.");
            } else {
                listaLineaPedidos = pedido.getLineaPedido();
                listaLineaPedidos.size();
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                System.out.println("Error en obtenerLineasPedidosByIdPedido.");
            }
        } finally {
            entityManager.close();
        }
        return listaLineaPedidos;
    }

    @Override
    public void agregarLineaPedido(List<LineaPedido> listaLineaPedidos, int idPedido) throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, idPedido);
            if (pedido == null) {
                throw new SQLException("El Pedido es null.");
            } else {
                for (LineaPedido lineaPedido : listaLineaPedidos) {
                    lineaPedido.setPedido(pedido);
                    entityManager.persist(lineaPedido);
                }
                pedido.setLineaPedido(listaLineaPedidos);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                System.out.println("Error en agregarLineaPedido.");
            }
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Pedido findPedidoByIdClient(int idCliente) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPedidoByIdClient'");
    }

    


}