
package servidorC;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InfoPC {
    private DataInputStream Entrada;
    private DataOutputStream Salida;
    private int numero;
    private int prioridad;

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public DataInputStream getEntrada() {
        return Entrada;
    }

    public void setEntrada(DataInputStream Entrada) {
        this.Entrada = Entrada;
    }

    public DataOutputStream getSalida() {
        return Salida;
    }

    public void setSalida(DataOutputStream Salida) {
        this.Salida = Salida;
    }
    
  
    public InfoPC()
    {
        
    }
    
}
