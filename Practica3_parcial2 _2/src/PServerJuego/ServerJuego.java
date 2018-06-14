package PServerJuego;

import PClienteJuego.ClienteJuego;
import PClienteJuego.Mensaje;
import static PServerJuego.vistaServerJuego1.m1;
import com.dist.DTO.BDJugador;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class ServerJuego {

    private int PUERTO; //Puerto para la conexión
    protected ServerSocket ss;
    protected Socket sock;
    private boolean cambios;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private int Num_Jugadores;
    private Jugadores JugadorActual;
    private static ArrayList<Jugadores> ConjuntoJugadores;
    protected static Mazo mazoEnviar;
    
    Thread t;
    atenderCliente t1;

    public ServerJuego(int puerto) {
        this.PUERTO = puerto;
        this.cambios = false;
        this.Num_Jugadores = 0;
        this.ConjuntoJugadores = new ArrayList<Jugadores>();
    }

    public void iniciar() // inicializa el servidor 
    {
        try {
            ss = new ServerSocket(PUERTO);
            System.out.println("Servidor en funcionamiento");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public void acceptar() {
        Mensaje buffer; 
        System.out.println("acceptar: esperando");
        try {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        buffer = recibirMSG();
        JugadorActual = new Jugadores(); // solo para agregar de manera temporal un jugador para despues pasarlo al arreglo       
        Num_Jugadores++;
        JugadorActual.setEntrada(entrada);
        JugadorActual.setSalida(salida);
        JugadorActual.setJugador(Num_Jugadores);
        JugadorActual.setSock(sock);
        ConjuntoJugadores.add(JugadorActual);
        t = new atenderCliente(JugadorActual, buffer);
        t.start();
    }
   
    private Mensaje recibirMSG()
    {
        Mensaje ms = new Mensaje();
        ObjectInputStream ob;
        try {
            ob = new ObjectInputStream(sock.getInputStream());
            ms = (Mensaje) ob.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(ClienteJuego.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return ms;
    }
    
    

    public int getNum_Jugadores() {
        return Num_Jugadores;
    }

    public Socket getSock() {
        return sock;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public ArrayList<Jugadores> getConjuntoJugadores() {
        return ConjuntoJugadores;
    }

    public void setMazoServidor(Mazo m) {
        //System.out.println("------- Method: setMazoServidor --------- ");

        this.mazoEnviar = m;
        //System.out.println("checando si hay datos: " + mazoEnviar.getCartas().get(0).getNombre());
        //System.out.println("checando si hay datos: " + mazoEnviar.getCartas().get(1).getNombre());
    }

}
