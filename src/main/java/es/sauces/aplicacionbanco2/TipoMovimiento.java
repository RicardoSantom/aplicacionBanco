package es.sauces.aplicacionbanco2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author daw1
 */
public enum TipoMovimiento {
    INGRESO ("I"),REINTEGRO ("R"),TRANSFERENCIA ("T");
    private final String codigo;
    
    private TipoMovimiento(String codigo){
        this.codigo=codigo;
    }
    public String getCodigo(){
        return codigo;
    }
}
