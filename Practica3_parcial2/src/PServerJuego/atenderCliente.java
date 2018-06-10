package PServerJuego;

import PClienteJuego.ClienteJuego;
import PClienteJuego.Mensaje;
import com.dist.DTO.BDJugador;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class atenderCliente extends Thread {

    private DataInputStream entrada;
    private DataOutputStream salida;
    protected Socket sock;
    private int jugador;
    private Mensaje mensaje;
    
    public atenderCliente(Jugadores j, Mensaje buffer) {
        this.entrada = j.getEntrada();
        this.salida = j.getSalida();
        this.sock = j.getSock();
        this.jugador = j.getJugador();
        this.mensaje = buffer;
    }

    @Override
    public void run() {
        Mensaje ms = new Mensaje();
        String cadenas[];
        int ncarta;
        int njugadorBD;
        System.out.println("atenderCliente: Nuevo Hilo atender cliente");
        System.out.println("atenderCliente: jugador " + jugador + ": " + mensaje.getProposito() +"+"+ mensaje.getInformacion());
        switch (mensaje.getProposito())
        {
            case "CONE": //primera conexion, se registra, y se responde numero jugador
                BDJugador bdJ = new BDJugador();
                cadenas = mensaje.getInformacion().split(":");
                njugadorBD = bdJ.obtenerUltimoJ();
                njugadorBD++;
                System.out.println("Respondiendo con:" + njugadorBD);
                bdJ.guardarJuagador(njugadorBD, cadenas[0], Integer.valueOf(cadenas[1])); //juador ip puerto
                ms.setProposito("CONE");
                ms.setInformacion(""+njugadorBD);
                ms.setHora(vistaServerJuego1.rel.imprimeHora());
                enviarMSG(ms);
                System.out.println("atenderCliente:"+ms.getProposito()+ms.getInformacion());
                break;
        
            case "CART": //se le envia las cartas de la ronda
                System.out.println("pidio cartas");
                ms.setProposito("CART");
                ms.setHora(vistaServerJuego1.rel.imprimeHora());
                ms.setInformacion(String.valueOf(vistaServerJuego1.numCartas)); // ronda
                ms.setMazo1(ServerJuego.mazoEnviar);
                enviarMSG(ms);
                break;
                
            case "DES":
                ncarta = Integer.valueOf( mensaje.getInformacion());
                System.out.println("Desactivando Carta:"+ mensaje.getInformacion());
                vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(ncarta).setActiva(false);
                if (vistaServerJuego1.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
                    System.out.println("Generando Nuevas Cartas");
                    vistaServerJuego1.jbtnSelecCartas.doClick();
                }
                break;
            default:
                System.out.println("Err");
                break;
        }
        
        System.out.println("atenderCliente: terminando hilo");
        try {
            // closing resources
            this.entrada.close();
            this.salida.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void enviarToken()
    {
        Mensaje ms = new Mensaje();
        ms.setProposito("TOKE");
        enviarMSG(ms);
    }


    private void enviarCarta(Mazo m) {
        ObjectOutputStream ob = null;
        try {
            ob = new ObjectOutputStream(sock.getOutputStream());
            ob.writeObject(m);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarRonda(int ronda) {
        try {
            salida.writeInt(ronda);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public Mensaje recibirMSG()
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
        return null;
    }
    
    public void enviarMSG(Mensaje ms)
    {
        ObjectOutputStream ob = null;
        try {
            ob = new ObjectOutputStream(sock.getOutputStream());
            ob.writeObject(ms);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
