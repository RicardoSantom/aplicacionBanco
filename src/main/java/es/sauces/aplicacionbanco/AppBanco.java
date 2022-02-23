/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Santiago Tomé
 *
 */
public class AppBanco {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Sauces");
        int opcion, opcion2 = 0;
        Scanner teclado = new Scanner(System.in);
        List<Cuenta> listado;
        Cuenta cuenta1, cuenta2;
        CuentaCredito cc;
        boolean correcto;
        //casting
        /*((CuentaCredito)cuenta1).getLimiteCredito()*/

        for (TipoMovimiento tp : TipoMovimiento.values()) {
            System.out.println(tp);
        }

        do {
            System.out.println("1.Abrir cuenta");
            System.out.println("2.Operar cuenta");
            System.out.println("3.Cancelar cuenta");
            System.out.println("4.Listar cuentas");
            System.out.println("5.Consultar total depositos");
            System.out.println("0.Salir");
            System.out.println("Introduzca opcion:");
            while (!teclado.hasNextInt()) {
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    String titular,
                     codigo;
                    float saldo;
                    System.out.println("1.Abrir cuenta");
                    codigo = pedirCodigo("Introduzca codigo de cuenta:");
                    System.out.println("Introduzca titular de la cuenta");
                    titular = teclado.nextLine();
                    System.out.println("Introduzca saldo inicial.");
                    saldo = teclado.nextFloat();
                     {
                        try {
                            if (banco.abrirCuenta(codigo, titular, saldo)) {
                                System.out.println("Cuenta creada");
                            } else {
                                System.out.println("No se ha podido crear la cuenta");
                            }
                        } catch (SaldoException ex) {
                            System.out.println(ex.getMessage());
                        } catch (InputMismatchException ime) {
                            System.out.println(ime.getMessage());
                        }
                    }
                    break;

                case 2:
                    System.out.println("2.Operar cuenta");
                    codigo = pedirCodigo("Introduzca codigo de cuenta para operar:");
                    cuenta1 = banco.getCuenta(codigo);
                    if (cuenta1 != null) {
                        do {
                            System.out.println("1.Ingresar dinero");
                            System.out.println("2.Retirar dinero");
                            System.out.println("3.Consultar saldo");
                            System.out.println("4.Realizar transferencia");
                            System.out.println("5.Consultar movimientos");
                            System.out.println("0.Atrás.");
                            System.out.println("Introduzca opcion:");
                            do {
                                correcto = true;
                                try {
                                    opcion2 = teclado.nextInt();
                                } catch (InputMismatchException ime) {
                                    correcto = false;
                                } finally {
                                    teclado.nextLine();
                                }
                            } while (!correcto);

                            teclado.nextLine();
                            switch (opcion2) {
                                case 1:
                                    float cantidad;
                                    System.out.println("1.Ingresar dinero");
                                    cantidad = pedirFloat("Introduzca cantidad a ingresar");
                                    cuenta1.ingresar(cantidad);
                                    System.out.println("Cantidad ingresada. Saldo=" + cuenta1.getSaldo());
                                    break;
                                case 2:
                                    System.out.println("2.Retirar dinero");
                                    System.out.println("Introduzca cantidad a reintegrar.");
                                    cantidad = teclado.nextFloat();
                                    try {
                                        cuenta1.reintegrar(cantidad);
                                        System.out.println("Saldo=" + cuenta1.getSaldo());
                                    } catch (SaldoException ex) {
                                        System.out.println(ex.getMessage());
                                    } catch (IllegalArgumentException iae) {
                                        System.out.println(iae.getMessage());
                                    } catch (NullPointerException nue) {
                                        System.out.println(nue.getMessage());
                                    }

                                    System.out.println("Cantidad retirada. Saldo=" + cuenta1.getSaldo());
                                    break;

                                case 3:
                                    System.out.println("3.Consultar saldo");
                                    System.out.println(cuenta1.getSaldo());
                                    break;
                                case 4:
                                    System.out.println("4.Realizar transferencia");
                                    codigo = pedirCodigo("Introduzca codigo de cuenta destino:");
                                    cuenta2 = banco.getCuenta(codigo);
                                    if (cuenta2 != null) {
                                        cantidad = pedirFloat("Introduzca cantidad a transferir");
                                        try {
                                            cuenta1.realizarTransferencia(cuenta2, cantidad);
                                        } catch (SaldoException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    } else {
                                        System.out.println("Error en la transferencia");
                                    }
                                    System.out.println("Saldo =" + cuenta1.getSaldo());
                                    System.out.println("mensaje prueba");
                                    teclado.nextLine();
                                    break;
                                case 5:
                                    System.out.println("5.Consultar movimientos");
                                    codigo = pedirCodigo("Introduzca codigo de cuenta para consultar movimientos.");
                                    System.out.println();
                                    if (cuenta1.getCodigo().equals(codigo)) {
                                        System.out.println(cuenta1.listarMovimientos());
                                    } else {
                                        System.out.println("No hay una cuenta con este código.");
                                    }
                                    break;
                                case 0:
                                    System.out.println("Hasta pronto.");
                                    break;
                                default:
                                    System.out.println("Opción incorrecta.");
                            }
                        } while (opcion2 != 0);
                    } else {
                        System.out.println("No existe una cuenta con ese código.");
                    }
                    break;

                case 3:
                    System.out.println("3.Cancelar cuenta");
                    codigo = pedirCodigo("Introduzca codigo de cuenta");
                    if (banco.cancelarCuenta(codigo)) {
                        System.out.println("Cuenta cancelada");
                    }
                    break;
                case 4:
                    System.out.println("4.Listar cuentas");
                    listado = banco.getCuentas();
                    for (Cuenta c : listado) {
                        System.out.println(c);
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.println("5.Consultar total depositos");
                    System.out.println("Total depósitos:" + banco.getTotalDepositos());
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);
    }

    private static String pedirCodigo(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        String codigo;
        do {
            System.out.println(mensaje);
            codigo = teclado.nextLine();
        } while (codigo.length() <= 0);
        return codigo;
    }

    private static float pedirFloat(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        float n;
        System.out.println(mensaje);
        while (!teclado.hasNextFloat()) {
            teclado.nextLine();
        }
        n = teclado.nextFloat();
        teclado.nextLine();
        return n;
    }

}
