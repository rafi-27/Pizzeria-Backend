package ies.thiar.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Pagable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    public abstract void pagar(double cantidad);
    public abstract int formaPago();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
