package PClienteJuego;

import PServerJuego.atenderCliente;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
    private InfoServidor Servidores;
    private ArrayList<InfoIntermedio> Info;
    private int arr[] = new int[4];
    
    public ClienteJuego(String host, int puerto, int jugador) {
        this.Servidores = new InfoServidor();
        this.jugador = jugador;
        this.Info = new ArrayList<InfoIntermedio>();
        arr[0] = - 1;
        arr[1] = - 1;
        arr[2] = - 1;
        arr[3] = - 1;
        
    }

    public void iniciar(String Puerto_Token) {
        Mensaje MSG1 = new Mensaje();
        Mensaje MSG2 = new Mensaje();
        StringBuilder tem1 = new StringBuilder();
        try {
            InetAddress tem = InetAddress.getLocalHost();
            IP = tem.getHostAddress();
            System.out.println("IP del equipo := " + IP);
        } catch (Exception e) {}
        MSG1.setProposito("CONE");
        
        tem1.append(IP);
        tem1.append(":");
        tem1.append(Puerto_Token);
        tem1.append(":");
        tem1.append(String.valueOf(jugador));
        
        MSG1.setInformacion(tem1.toString());
        
        System.out.println("iniciar: " + tem1.toString());
        enviarMSG(MSG1);
        MSG2 = recibirMSG();
        System.out.println("InterpretarMensaje: jugador "+ MSG2.getInformacion());
        jugador = Integer.valueOf(MSG2.getInformacion());
    }
    
    public Carta SeleccionarCarta (String elegido, Mazo mazoRecibido)
    {
        System.out.println("Boton elegido " + elegido);
        Mensaje ms = new Mensaje();
        int ncarta;
        ms.setProposito("DES");
        ms.setNumeroJugador(jugador);
        switch (elegido) 
        {
            case "seleccion1":
                ms.setInformacion("1");
                ncarta = 0;
                break;
            case "seleccion2":
                ms.setInformacion("2");
                ncarta = 1;
                break;                
            case "seleccion3":
                ms.setInformacion("3");
                ncarta = 2;
                break;                
            default:
                System.out.println("SeleccionarCarta: error de seleccion");
                return null;
        }
        enviarMSG(ms);
        return mazoRecibido.getCartas().get(ncarta);
        
    }
    
    public void pedirCartas ()
    {
        Mensaje ms1 = new Mensaje();
        Mensaje ms2 = new Mensaje();
        ms1.setProposito("CART");
        ms1.setInformacion("solicitando cartas");
        enviarMSG(ms1);
        ms2 = recibirMSG();
        m = ms2.getMazo1();
        
    }
   
    public int InterpretarMensaje()
    {
        return 0;
    }

    public void enviarMSG(Mensaje ms)
    {
        Conexion();
        InfoIntermedio x = new InfoIntermedio();
        x.setHora(vistaClienteJuego1.rel.imprimeHora());
        System.out.println("Escribiento:"+ ms.getProposito()+ms.getInformacion());
        x.setInfo("Enviando:"+ ms.getProposito()+ms.getInformacion());
        ObjectOutputStream ob = null;
        try {
            ob = new ObjectOutputStream(sock.getOutputStream());
            ob.writeObject(ms);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Info.add(x);
    }
    
    public Mensaje recibirMSG()
    {
        InfoIntermedio x = new InfoIntermedio();
        Mensaje ms = new Mensaje();
        ObjectInputStream ob;
        x.setHora(vistaClienteJuego1.rel.imprimeHora());
        
        try {
            x.setInfo("Recibiendo:"+ ms.getProposito()+ms.getInformacion());
            ob = new ObjectInputStream(sock.getInputStream());
            ms = (Mensaje) ob.readObject();
            System.out.println(ms.getProposito()+ms.getInformacion());
            Info.add(x);
            return ms;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
  
    private void Conexion()
    {
        int numero;
        InfoIntermedio x = new InfoIntermedio();
        while(true)
        {
            numero = (int) (Math.random() * 4);
            x.setHora(vistaClienteJuego1.rel.imprimeHora());
            try {
                sock = new Socket(Servidores.getIP(numero), Servidores.getPuerto(numero));
                Entrada = new DataInputStream(sock.getInputStream());
                salida = new DataOutputStream(sock.getOutputStream());
                System.out.println("Conexion exitosa("+Servidores.getIP(numero)+" : "+Servidores.getPuerto(numero)+")");
                x.setInfo("Conexion exitosa("+Servidores.getIP(numero)+" : "+Servidores.getPuerto(numero)+")");
                Info.add(x);
                break;
            } catch (UnknownHostException e) {
                System.out.println("El host no existe o no está activo.");
                                
            } catch (IOException e) {
                System.out.println("Tratando conexion");                
            }
            
        }        
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

    public void setSock(Socket sock) {
        this.sock = sock;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public void setEntrada(DataInputStream Entrada) {
        this.Entrada = Entrada;
    }

    public ArrayList<InfoIntermedio> getInfo() {
        return Info;
    }
    
    
}
