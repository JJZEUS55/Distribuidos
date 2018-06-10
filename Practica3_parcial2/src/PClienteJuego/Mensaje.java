
package PClienteJuego;

import com.dist.juego.Mazo;
import java.io.Serializable;

public class Mensaje implements Serializable{
    private String Proposito;
    private String Informacion;
    private String Hora;
    private Mazo Mazo1;
    private int NumeroJugador;
    
    public Mensaje ()
    {
        this.Proposito = "NADA";
        this.Informacion = "";
        this.Mazo1 = null;
        this.Hora = "00:00:00";    
        this.NumeroJugador = 0;
    }

    public String getProposito() {
        return Proposito;
    }

    public void setProposito(String Proposito) {
        this.Proposito = Proposito;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String Informacion) {
        this.Informacion = Informacion;
    }

    public Mazo getMazo1() {
        return Mazo1;
    }

    public void setMazo1(Mazo Mazo1) {
        this.Mazo1 = Mazo1;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public int getNumeroJugador() {
        return NumeroJugador;
    }

    public void setNumeroJugador(int NumeroJugador) {
        this.NumeroJugador = NumeroJugador;
    }
    
    
    
    
}
