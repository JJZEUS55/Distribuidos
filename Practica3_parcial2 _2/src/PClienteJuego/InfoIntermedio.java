package PClienteJuego;

/**
 *
 * @author Alan
 */
public class InfoIntermedio { 
    private String info;
    private String hora;

    public InfoIntermedio() {
        this.info = "//";
        this.hora = "00:00";
    }

    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
