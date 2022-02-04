
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.time.LocalDate;

/**
 *
 * @Ricardo Santiago Tomé
 */
public class Movimiento {

    private LocalDate fecha;
    private char tipo;
    private float cantidad;
    private float saldo;

    /**
     * La clase Movimiento construye objetos que registran los ingresos,
     * reintegros y transferencias de las instancias de la clase Cuenta. El
     * constructor inicialilza las variables listadas a continuacion.
     *
     * @param fecha de tipo LocalDate registra la hora en que se reallizó un
     * movimiento.
     * @param tipo es un tipo primivito char y especifica si se trata de un
     * ingreso='I¡,reintegro='R' o transferencia='T'.
     * @param cantidad de tipo float, se utiliza para recoger la cantidad que se
     * reintegra, ingresa o transfiere en saldo.
     * @param saldo de tipo float, representa el dinero existente en el objeto
     * Movimiento.
     */
    public Movimiento(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    /**
     *
     * @return la fecha presente en las instancias de la clase Movimiento
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     *
     * @return el tipo de movimiento dentro del objeto de la clase Movimiento.
     * ngreso='I¡,reintegro='R' o transferencia='T'.
     */
    public char getTipo() {
        return tipo;
    }

    /**
     *
     * @return la cantidad que se ingresa,reintegra o transfiere al objeto del
     * tipo Cuenta y queda registrada en el objeto del tipo Movimiento.
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     *
     * @return el montante (o quizás criptomonedas...) disponibles en una
     * Cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    @Override
    /**
     * El método sobreescrito toString nos devuelve una cadena con la
     * información del objeto del tipo Movimiento, sus parámetros son:
     *
     * @param fecha en la que se realiza el movimiento.
     * @param tipo tipo de movimiento dentro del objeto de la clase Movimiento.
     * ngreso='I¡,reintegro='R' o transferencia='T'.
     * @param cantidad la cantidad que se ingresa,reintegra o transfiere al
     * objeto del tipo Cuenta y queda registrada en el objeto del tipo Movimiento
     * @param saldo float que representa la cantidad de activos presentes en el
     * objeto de la clase Cuenta
     */
    public String toString() {
        return fecha + "," + tipo + "," + cantidad + "=" + saldo;
    }

}
