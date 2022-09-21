package ies.sauces.aplicacionbanco;

import java.util.Comparator;
public class ComparadorSaldo implements Comparator<Cuenta>{

    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        int salida;
        
        if(o1.getSaldo()<o2.getSaldo()){
            salida=-1;
        }
        else if(o1.getSaldo()<o2.getSaldo()){
            salida=0;
        }
        else{
            salida=-1;
        }
        return salida;
    }
    
}
