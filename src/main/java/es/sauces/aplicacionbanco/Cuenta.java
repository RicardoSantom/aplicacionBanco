/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @Ricardo Santiago Tomé La clase Cuenta crea objetos con un codigo, titular,
 * un saldo y los movimientos que dentro de la cuenta se realizan.
 */
public class Cuenta implements Comparable<Cuenta>{

    private String codigo;
    private String titular;
    private float saldo;
    private List<Movimiento> movimientos;

    public Cuenta() {
    }
    
    

    /**
     *
     * @param codigo String que identifica la Cuenta como única.
     * @param titular String que asocia una Cuenta a un propietario.
     * @param saldo float que representa la cantidad de activos presentes en el
     * objeto de la clase Cuenta
     */
    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        if (saldo >= 0) {
            this.saldo = saldo;
        }
        movimientos = new ArrayList<>();
        movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.INGRESO, saldo, saldo));
    }

    /**
     *
     * @return el codigo de tipo String que identifica una instancia de la clase
     * Cuenta de otra.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return una cadena de caracteres que identifican al titular de la cuenta.
     */
    public String getTitular() {
        return titular;
    }

    /**
     * @return un float que se corresponde con la cantidad presente en la
     * instancia de la clase Cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     *
     * @return una lista con los movimientos realizados en la instancia de la
     * clase Cuenta.
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     *
     * @param desde LocalDate que recibe el metodo para establecer la fecha
     * desde la que queremos obtener un listado de los movimientos de la Cuenta
     * @param hasta LocalDate recibido por este método que establece la fecha
     * tope hasta la que queremos tener información de movimientos.
     * @return un ArrayList con el listado de movimientos realizados
     * comprendidos entre los fechas pasadas como argumento desde y hasta.
     */
    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta) {
        List<Movimiento> salida = new ArrayList<>();
        Iterator<Movimiento> iterador = movimientos.iterator();
        Movimiento m;
        while (iterador.hasNext()) {
            m = iterador.next();
            if (m.getFecha().isAfter(desde) && m.getFecha().isBefore(hasta)) {
                salida.add(m);
            }
        }
        return salida;
    }

    /**
     *
     * @param codigo String que cambia de al codigo preexistente sustituyéndolo
     * por este nuevo codigo recibido como parámetro.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @param saldo float que cambia el saldo preexistente sustituyéndolo por
     * este nuevo saldo recibido como parámetro.
     */
    public void setSaldo(float saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
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
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }

    /**
     * Método que permite ingresar dinero en la cuenta.
     *
     * @param cantidad de tipo float a ingresar en la cuenta. evalua si el float
     * cantidad recibido como parámetro es un decimal positivo, de ser cierto
     * incrementa la cuantía del saldo del objeto Cuenta con la cantidad
     * recibida. A continuación, crea una nueva instancia de la clase Movimiento
     * con los datos necesarios para su construcción.
     */
    public void ingresar(float cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.INGRESO, cantidad, saldo));
        }
    }

    /**
     *
     * @param cantidad de tipo float que si cumple las condiciones de ser un
     * decimal positivo y al mismo tiempo ser de una cuantía menor o igual al
     * saldo existente en la cuenta, detrae esta cantidad del saldo de la
     * cuenta. También crea una nueva instancia de la clase Movimiento con los
     * datos necesarios para su construcción.
     */
    public void reintegrar(float cantidad) {
        if(cantidad>saldo){
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        saldo -= cantidad;
        movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.REINTEGRO, -cantidad, saldo));
    }

    /**
     *
     * @param destino da al método un objeto de la clase Cuenta.
     * @param cantidad da al método un float que será la cantidad con la que el
     * método operará. Si esta cantidad recibida com parámetro es un número
     * decimal positivo y al tiempo es menor o igual que el saldo preexistente
     * en el objeto del tipo Cuenta, detraerá esta cantidad del saldo de la
     * Cuenta de origen y lo incrementará en el saldo de la Cuenta destino
     * recibida como parámetro. Posteriormente crea dos nuevas instancias de la
     * clase movimientos. En la primera, registra la hora en el momento de
     * realizar el movimiento, un char de nombre 'tipo' que identifica la
     * naturaleza de la transferencia, la cantidad decrementada en la cuenta de
     * origen y el saldo de esta cuenta de origen después del decremento. La
     * segunda instancia de Movimientos añade un nuevo registro con la fecha
     * actual, el char identificativo de la operación, la cantidad que se ha
     * transferido y el saldo de la cuenta de destino después de la
     * transferencia.
     */
    public void realizarTransferencia(Cuenta destino, float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            destino.saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.TRANSFERENCIA, -cantidad, saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.TRANSFERENCIA, cantidad, destino.saldo));
        }
    }

    /**
     *
     * @return una cadena resultante de aplicar el método toString a un objeto
     * de tipo StrinBuilder que a través de un bucle for hemos llenado
     * previamente con todos los movimientos realizados en la cuenta.
     */
    public String listarMovimientos() {
        StringBuilder sb = new StringBuilder();
        for (Movimiento m : movimientos) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Cuenta o) {
        return this.codigo.compareTo(o.codigo);
    }

}
