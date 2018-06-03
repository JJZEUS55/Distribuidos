package PClienteJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Alan
 */
public class ClienteJuego {
    private Socket sock;
    private String IP;
    private String IP_siguiente;
    private int puerto_siguiente;
    private int jugador;
    private int prioridad;
    private DataOutputStream salida; // enviar mensajes
    private DataInputStream Entrada; //recibir mensajes
    private Intermedio in;
    public ClienteJuego() 
    {
        in = new Intermedio();
    }
    
    public void iniciar (String Puerto_Token)
    {
        in.Conexion();
        StringBuilder tem1 = new StringBuilder();  
        try
        {
            InetAddress tem = InetAddress.getLocalHost();
            IP = tem.getHostAddress();
            System.out.println("IP del equipo := "+IP);
        }
        catch(Exception e){}
        tem1.append(IP);
        tem1.append(":");
        tem1.append(Puerto_Token);
        System.out.println("iniciar: "+ tem1.toString());
        enviarMSJ(tem1.toString());
                
    }
    
    public int IterprestarMensaje() //aqui se puede saber si el servidor principal murio
    {                               //al caerse el buffer tendra basura y caera en el caso de defaul 0
        String buffer; 
        buffer = recibirMSJ();
        System.out.println("Del server:"+buffer);
        switch(buffer)
        {
            case "info":
                jugador =  Integer.valueOf(recibirMSJ()); // recibe su numero de jugador
                IP_siguiente = recibirMSJ(); //IP del siguiente
                puerto_siguiente = Integer.valueOf(recibirMSJ()); // puerto del siguiente
                System.out.println("InterpretarMensaje: jugador "+ jugador);
                System.out.println("InterpretarMensaje: recibiendo informacion del siguiente Jugador["+IP_siguiente+"]["+puerto_siguiente+"]");               
                return 1;
            case "token":
                System.out.println("El servidor tenia el token asi que lo ha enviado :v ");
                return 2;
            case "cartas":
                System.out.println("InterprentarMensje: cartas del servidor");
                //System.out.println(recibirMSJ());
                return 3;
            case "hora":
                vistaClienteJuego.rel.modificarHora(recibirMSJ());
                return 4;
            default:
                return 0;
        }

    }
    
    public void Cartas()
    {
        in.Conexion();
        enviarMSJ("cartas");
    }
    
    
    public void enviarMSJ(String buffer)
    {
         try {
            salida.writeUTF(buffer);
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    public String recibirMSJ() 
    {
        String buffer = "";
        try {
            buffer = Entrada.readUTF();
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");    
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public String getIP_siguiente() {
        return IP_siguiente;
    }

    public void setIP_siguiente(String IP_siguiente) {
        this.IP_siguiente = IP_siguiente;
    }

    public int getPuerto_siguiente() {
        return puerto_siguiente;
    }

    public void setPuerto_siguiente(int puerto_siguiente) {
        this.puerto_siguiente = puerto_siguiente;
    }   

    public String getIP() {
        return IP;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public void setEntrada(DataInputStream Entrada) {
        this.Entrada = Entrada;
    }

    public void setSock(Socket sock) {
        this.sock = sock;
    }
    
    
}
