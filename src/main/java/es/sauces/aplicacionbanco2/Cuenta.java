/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
    private List<Movimiento> movimientos;

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
    
    public List<Movimiento> getMovimientos(LocalDate desde,LocalDate hasta){
        List<Movimiento> salida=new ArrayList<>();
        Iterator<Movimiento> iterador=movimientos.iterator();
        Movimiento m;
        while(iterador.hasNext()){
            m=iterador.next();
            if(m.getFecha().isAfter(desde) && m.getFecha().isBefore(hasta)){
                salida.add(m);
            }
        }
        return salida;
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
    
    public void ingresar(float cantidad){
        if(cantidad>0){
            saldo+=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),'I',cantidad,saldo));
        }
    }
    
    public void reintegrar(float cantidad){
        if(cantidad>0 && cantidad<=saldo){
            saldo-=cantidad;
             movimientos.add(new Movimiento(LocalDate.now(),'R',-cantidad,saldo));
        }
    }
    
    public void realizarTransferencia(Cuenta destino,float cantidad){
        if(cantidad > 0 && cantidad <=saldo){
            saldo-=cantidad;
            destino.saldo+=cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),'T',-cantidad,saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(),'T',cantidad,destino.saldo));
        }
    }
    
    public String listarMovimientos(){
        StringBuilder sb=new StringBuilder();
        for(Movimiento m: movimientos){
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
    
}
