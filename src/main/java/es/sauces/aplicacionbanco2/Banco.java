/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.util.List;

/**
 *
 * @author daw1
 */
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(String nombre, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.cuentas = cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
