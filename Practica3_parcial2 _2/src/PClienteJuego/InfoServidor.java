
package PClienteJuego;
    

public class InfoServidor {
    private String IP[];
    private int puerto[];
    
    public InfoServidor() {
        IP = new String[4];
        puerto = new int[4];
        IP[0] = "localhost";
        puerto[0] = 3101;
        IP[1] = "localhost";
        puerto[1] = 3102;
        IP[2] = "localhost";
        puerto[2] = 3103;
        IP[3] = "localhost";
        puerto[3] = 3104;
    }
    
    public String getIP(int i) {
        return IP[i];
    }

    public int getPuerto(int i) {
        return puerto[i];
    }
    
}
