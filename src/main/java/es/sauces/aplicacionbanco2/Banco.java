/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author daw1
 */
public class Banco {

    private String nombre;
    private Set<Cuenta> cuentas;

    /**
     *
     * @param nombre
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public List<Cuenta> getCuentas() {
        List <Cuenta> listaCuenta= new ArrayList<>(cuentas);
        return listaCuenta;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     *
     * @param codigo
     * @param titular
     * @param saldo
     * @return
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo) {
         return cuentas.add(new Cuenta(codigo, titular, saldo));
    }
    
    /**
     *
     * @param codigo
     * @return
     */
    public Cuenta getCuenta(String codigo){
        Cuenta c=null,aux;
       for(Cuenta c1: cuentas){
           if(c1.getCodigo().equals(codigo)){
               c=c1;
               break;
           }
       }
       return c;
    }
    
    /**
     *
     * @param codigo
     * @return
     */
    public boolean cancelarCuenta(String codigo){
        boolean salida;
        salida=false;
        Cuenta c=getCuenta(codigo);
        if(c!=null){
            salida=cuentas.remove(c);
        }
        return salida;
    }
    
    /**
     *
     * @return
     */
    public float getTotalDepositos(){
        float acumulador=0;
        
        for(Cuenta c: cuentas){
            acumulador+=c.getSaldo();
        }
        return acumulador;
    }

    /*Con set no es necesario este método
    private int buscarCuenta(String codigo) {
        int posicion = -1;
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getCodigo().equals(codigo)) {
                posicion = i;
            }
        }
        return posicion;
    }*/

}
