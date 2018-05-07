package servidorC;
import servidorC.InfoPC;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class clase_server {
    private int PUERTO; //Puerto para la conexión
    private String HOST; //Host para la conexión
    protected ServerSocket ss; //Socket del servidor
    protected Socket sock;
    DataInputStream entrada;
    DataOutputStream salida;
    String Tem [][] = new String [3][];
    private int JugadorAtendido;

    public clase_server() {
        PUERTO = 3080;
    }
    
    public clase_server(int val)
    {
        
    }        

    public void iniciar() 
    {      
        try 
        {
            ss = new ServerSocket(PUERTO);
            System.out.println("Esperando Conexion");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public InfoPC aceptar(int jugador) 
    {   
        InfoPC JugadorNuevo = new InfoPC();
        try 
        {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        System.out.println(recibirMSJ());
        JugadorNuevo.setNumero(jugador);
        JugadorNuevo.setEntrada(entrada);
        JugadorNuevo.setSalida(salida);
        System.out.println("accept check");
        return JugadorNuevo;
    }
    
    
    
    public void check (InfoPC ob[], int cant)
    {
        for (int i = 0; i < cant; i++) {
            entrada = ob[i].getEntrada();
            salida = ob[i].getSalida();
            enviarMSJ("ok5seg");
        }
    }
       
    private void enviarMSJ(String buffer)
    {
        try 
        {
            salida.writeUTF(buffer);
        }
        catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    private String recibirMSJ()
    {
        String buffer = "";
        try 
        {
            buffer = entrada.readUTF();
        }
        catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }
    
        
    public int getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }
}
