/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.sauces.aplicacionbanco2;

import java.util.List;
import java.util.Scanner;

public class AppBanco {

    public static void main(String[] args) {
        Banco banco = new Banco("Banco Sauces");
        int opcion, opcion2;
        Scanner teclado = new Scanner(System.in);
        List<Cuenta> listado;
        Cuenta cuenta1;

        do {
            System.out.println("1.Abrir cuenta");
            System.out.println("2.Operar cuenta");
            System.out.println("3.Cancelar cuenta");
            System.out.println("4.Listar cuentas");
            System.out.println("5.Consultar total depositos");
            System.out.println("0.Salir");
            System.out.println("Introduzca opcion:");
            while (teclado.hasNextInt()) {
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
                    System.out.println("Introduzca codigo de la cuenta");
                    codigo = teclado.nextLine();
                    System.out.println("Introduzca titular de la cuenta");
                    titular = teclado.nextLine();
                    System.out.println("Introduzca saldo de la cuenta");
                    while (teclado.hasNextFloat()) {
                        teclado.nextLine();
                    }
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                    if (banco.abrirCuenta(codigo, titular, saldo)) {
                        System.out.println("Cuenta creada");
                    } else {
                        System.out.println("No se ha podido crear la cuenta");
                    }
                    break;
                case 2:
                    System.out.println("2.Operar cuenta");
                    System.out.println("Introduzca codigo de cuenta para operar");
                    codigo = teclado.nextLine();
                    cuenta1 = banco.getCuenta(codigo);
                    if (cuenta1 != null) {
                        do {
                            System.out.println("1.Ingresar dinero");
                            System.out.println("2.Retirar dinero");
                            System.out.println("3.Consultar saldo");
                            System.out.println("4.Realizar transferencia");
                            System.out.println("5.Consultar movimientos");
                            System.out.println("0.Salir");
                            System.out.println("Introduzca opcion:");
                            while (teclado.hasNextInt()) {
                                teclado.nextLine();
                            }
                            opcion2 = teclado.nextInt();
                            teclado.nextLine();
                            switch (opcion2) {
                                case 1:
                                    float cantidad;
                                    System.out.println("1.Ingresar dinero");
                                    System.out.println("Introduzca cantidad a ingresar");
                                    while (teclado.hasNextFloat()) {
                                        teclado.nextLine();
                                    }
                                    cantidad = teclado.nextFloat();
                                    cuenta1.ingresar(cantidad);
                                    System.out.println("Cantidad ingresada. Saldo=" + cuenta1.getSaldo());
                                    break;
                                case 2:
                                    System.out.println("2.Retirar dinero");
                                    System.out.println("Introduzca cantidad a retirar");
                                    while (teclado.hasNextFloat()) {
                                        teclado.nextLine();
                                    }
                                    cantidad = teclado.nextFloat();
                                    cuenta1.reintegrar(cantidad);
                                    System.out.println("Cantidad retirada. Saldo=" + cuenta1.getSaldo());
                                    break;
                                case 3:
                                    System.out.println("3.Consultar saldo");
                                    System.out.println("Introduzca codigo de cuenta para consultar.");
                                    codigo = teclado.nextLine();
                                    if(cuenta1.getCodigo().equals(codigo)){
                                        System.out.println(cuenta1.getSaldo());
                                    }else{
                                        System.out.println("No hay una cuenta con este código.");
                                    }
                                    
                                    break;
                                case 4:
                                    System.out.println("4.Realizar transferencia");
                                    break;
                                case 5:
                                    System.out.println("5.Consultar movimientos");
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
                    System.out.println("Introduzca codigo de cuenta");
                    codigo = teclado.nextLine();
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

}
