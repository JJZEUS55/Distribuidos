
package PServerJuego;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author Alan
 */
public class Jugadores 
{
    private int jugador;
    private int puerto;
    private String ip;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }
    
    
}
