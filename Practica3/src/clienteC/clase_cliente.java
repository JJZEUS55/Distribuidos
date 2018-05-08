
package clienteC;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class clase_cliente 
{
    private Socket sock;
    private static String HOST = "localhost";
    private String IP;
    private int jugador;
    private DataOutputStream salida; // enviar mensajes
    private DataInputStream Entrada; //recibir mensajes
    private boolean servidorOK = true;
    private boolean statusCliente = false;
    private int prioridad;

    
    
    public clase_cliente(String host, int puerto) { // puerto 3080 para funcionamiento normal, 4000 modo bully
        try {
            sock = new Socket(host, puerto);
            Entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
            statusCliente = true;
            
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        //System.out.println("Se inicio cliente check");
        //enviarMSJ("hola");
    }
  
    public boolean check()
    {
        while(true)
        {
            System.out.println(recibirMSJ());
            if (servidorOK == false) 
                break;          
        }
        return servidorOK;
    }
    
    public boolean ProcesoSeleccion() // solo para el algoritmo de bully
    { 
        String buffer;
        prioridad = ((int) (Math.random() * 100) + 1);
        System.out.println("prioridad cliente de:"+prioridad);
        buffer = recibirMSJ(); // recibe prioridad del servidor
        System.out.println("Prioridad del servidor propuesto:" +buffer);
        if (Integer.valueOf(buffer) > prioridad) {
            System.out.println("cliente de menor prioridad, descartado para servidor");
            enviarMSJ("ok");
        }
        else
        {
            System.out.println("cliente de meyor prioridad, enviando propuesta");
            enviarMSJ(String.valueOf(prioridad));
            if(recibirMSJ().equals("Elegido"))
            {
                System.out.println("Elegido, iniciando nuevo servidor principal");
                return true;
            }
            else
            {
                System.out.println("Descartado para servidor");
                return false;
            }
        }
        return false;
    }
    

    
    private void enviarMSJ(String buffer)
    {
        try {
            salida.writeUTF(buffer);
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida. ENV");
            servidorOK = false;
        }
    }
    
    private String recibirMSJ() 
    {
        String buffer = "";
        try {
            buffer = Entrada.readUTF();
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida. REC");
            servidorOK = false;
            try {sock.close();}
            catch(IOException e1){}
        }
        return buffer;
    }
    
    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public boolean isStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(boolean statusCliente) {
        this.statusCliente = statusCliente;
    }
    
}
