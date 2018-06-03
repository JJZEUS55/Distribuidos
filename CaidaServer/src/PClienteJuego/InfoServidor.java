
package PClienteJuego;
    

public class InfoServidor {
    private String IP;
    private int puerto;

    public InfoServidor(String IP, int puerto) {
        this.IP = IP;
        this.puerto = puerto;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    
}
