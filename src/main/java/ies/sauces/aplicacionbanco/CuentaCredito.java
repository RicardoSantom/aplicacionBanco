/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ies.sauces.aplicacionbanco;

/**
 *
 * @author daw1
 */
public class CuentaCredito extends Cuenta{
    private float limiteCredito;

    public CuentaCredito(String codigo, String titular, float saldo,float limiteCredito) throws SaldoException {
        super(codigo, titular, saldo);
        this.limiteCredito = limiteCredito;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void reintegrar(float cantidad) {
        float nuevoSaldo;
        if(cantidad >= 0 ){
            nuevoSaldo=getSaldo()-cantidad;
            if(nuevoSaldo>=limiteCredito){
                try {
                    setSaldo(nuevoSaldo);
                } catch (SaldoException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+","+limiteCredito;
    }

    
}
