package ies.thiar.controlador.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ies.thiar.Modelo.EstadoPedido;
import ies.thiar.Modelo.LineaPedido;
import ies.thiar.Modelo.Pedido;
import ies.thiar.controlador.dao.PedidoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAPedidoDao implements PedidoDao{
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
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Pedido pedido) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Pedido findByID(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

    @Override
    public List<Pedido> obtenerPedidosByIdClient(int idCliente) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPedidosByIdClient'");
    }

    @Override
    public List<Pedido> obtenerPedidosByState(EstadoPedido state) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPedidosByState'");
    }

    @Override
    public List<LineaPedido> obtenerLineasPedidosByIdPedido(int idPedido) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerLineasPedidosByIdPedido'");
    }

    @Override
    public void agregarLineaPedido(Connection conexion, List<LineaPedido> listaLineaPedidos, int idPedido)
            throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarLineaPedido'");
    }

    @Override
    public Pedido findPedidoByIdClient(int idCliente) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPedidoByIdClient'");
    }
}
