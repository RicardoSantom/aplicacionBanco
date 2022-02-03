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
 * @author Ricardo
 */
public class Banco {

    private String nombre;
    private Set<Cuenta> cuentas;

    /**
     *
     * @param nombre se le pasa al método Banco para que añada este String como identificador de
     * la instancia de la clase Banco.
     * El método también cuenta en su constructor con un conjunto HashSet construido en base a un Set
     * de tipo Cuenta.
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new HashSet<>();
    }

    /**
     *
     * @return el nombre identificativo del objeto Banco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return devuelve una lista con las instancias de Cuenta
     * presentes en el programa.
     */
    public List<Cuenta> getCuentas() {
        List <Cuenta> listaCuenta= new ArrayList<>(cuentas);
        return listaCuenta;
    }

    /**
     *
     * @param nombre modifica el nombre preexistente del objeto Banco 
     * por este nuevo nombre recibido.
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
     * @param codigo String que identifica una cuenta como única.
     * @param titular String que identifica al dueño de la cuenta.
     * @param saldo decimal que representa la cantidad existente en una determinada Cuenta.
     * @return una nueva instancia de la clase Cuenta con los parámetros pasados al método.
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo) {
         return cuentas.add(new Cuenta(codigo, titular, saldo));
    }
    
    /**
     *
     * @param codigo se le facilita a este método para que evalúe con un bucle for each si el
     * código del objeto cuenta creado en su interior es igual al código de la instancia preexistente
     * de la clase Cuenta, asuma que estas cuentas son la misma.
     * @return una mueva instancia de la clase cuenta que habrá modificado o no su valor según
     * la evaluación a la que ha sido sometida.
     */
    public Cuenta getCuenta(String codigo){
        Cuenta c;
        c = null;
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
     * @param codigo que dentro del método es evaluado, si su valor es null el 
     * booleano salida conservará su condición de false y no aplicará la instrucción
     * de borrado de cuenta.
     * Por el contrario, si el objeto de la clase Cuenta si existe en base al código 
     * proporcionado, 'removerá' esta cuenta.
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
     * @return un float que abrá variado su valor inicial de =0
     * con un bucle for each que por cada instancia de la clase Cuenta, irá incrementando
     * el valor de acumulador con el saldo presente en cada una de las instancias de Cuenta.
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
