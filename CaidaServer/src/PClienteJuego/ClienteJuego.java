package PClienteJuego;

import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class ClienteJuego {

    private Socket sock;
    private static String HOST;
    private static int PUERTO;
    private String HoraServidor;
    private String IP;
    private String IP_siguiente;
    private int puerto_siguiente;
    private int jugador;
    private int prioridad;
    private DataOutputStream salida; // enviar mensajes
    private DataInputStream Entrada; //recibir mensajes
    private Mazo m;
    private int ronda;

    public ClienteJuego(String host, int puerto, int jugador) {
        this.jugador = jugador;
        try {
            sock = new Socket(host, puerto);
            Entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        System.out.println("ClienteJuego: Se ha conectado el cliente");
        
    }

    public void iniciar(String Puerto_Token) {
        StringBuilder tem1 = new StringBuilder();

        try {
            InetAddress tem = InetAddress.getLocalHost();
            IP = tem.getHostAddress();
            System.out.println("IP del equipo := " + IP);
        } catch (Exception e) {
        }
        tem1.append(IP);
        tem1.append(":");
        tem1.append(Puerto_Token);
        tem1.append(":");
        tem1.append(String.valueOf(jugador));
      
        System.out.println("iniciar: " + tem1.toString());
        enviarMSJ(tem1.toString());

    }

    public int IterprestarMensaje() //aqui se puede saber si el servidor principal murio
    {                               //al caerse el buffer tendra basura y caera en el caso de defaul 0
        String buffer;
        int jugadorTemporal;
        buffer = recibirMSJ();
        System.out.println("Del server:" + buffer);
        switch (buffer) {
            case "info":
                jugadorTemporal = Integer.valueOf(recibirMSJ()); // recibe su numero de jugador
                if (jugador == 0) {
                    jugador = jugadorTemporal;
                }
                IP_siguiente = recibirMSJ(); //IP del siguiente
                puerto_siguiente = Integer.valueOf(recibirMSJ()); // puerto del siguiente
                System.out.println("InterpretarMensaje: jugador " + jugador);
                System.out.println("InterpretarMensaje: recibiendo informacion del siguiente Jugador[" + IP_siguiente + "][" + puerto_siguiente + "]");
                return 1;
            case "token":
                System.out.println("InterprentarMensje: El servidor tenia el token asi que lo ha enviado :v ");
                return 2;
            case "cartas":
                System.out.println("InterprentarMensje: cartas del servidor");
                //System.out.println(recibirMSJ());
                ronda = recibirRonda();
                m = recibirCarta();

                System.out.println("Se ha recibido : " + m.getCartas().get(0).getNombre());
                System.out.println("Se ha recibido : " + m.getCartas().get(1).getNombre());
                System.out.println("Se ha recibido : " + m.getCartas().get(2).getNombre());
                System.out.println("La ronda actual es RONDA: " + ronda);
                return 3;
            case "hora":
                vistaClienteJuego.rel.modificarHora(recibirMSJ());
                return 4;
            default:
                return 0;
        }

    }

    public void enviarMSJ(String buffer) {
        try {
            salida.writeUTF(buffer);
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de entrada/salida.");
        }
    }

    public String recibirMSJ() {
        String buffer = "";
        try {
            buffer = Entrada.readUTF();
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }

    public Mazo recibirCarta() {
        System.out.println("---- Entrando a Recibir Carta -----");
        Mazo m = null;
        ObjectInputStream ob;
        try {
            ob = new ObjectInputStream(sock.getInputStream());
            m = (Mazo) ob.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public int recibirRonda() {
        int ronda = 0;
        try {
            ronda = Entrada.readInt();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ronda;
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

    public Mazo getMazoCliente() {
        return this.m;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

}
