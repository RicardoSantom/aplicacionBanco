/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daw1
 */
public class Cuenta {
    private String codigo;
    private String titular;
    private float saldo;
    List<Movimiento> movimientos;

    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
        movimientos=new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitular() {
        return titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }
    
    
    
    
}
