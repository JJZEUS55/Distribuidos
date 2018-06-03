package PServerJuego;

import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class atenderCliente extends Thread {

    private DataInputStream entrada;
    private DataOutputStream salida;
    protected Socket sock;
    private int jugador;

   
    public atenderCliente(Jugadores j) {
        this.entrada = j.getEntrada();
        this.salida = j.getSalida();
        this.sock = j.getSock();
        this.jugador = j.getJugador();
    }

    @Override
    public void run() {
        System.out.println("----- Class Atender Cliente -------");
        System.out.println("Nuevo Hilo atender cliente");
        String buffer;
        while (true) {
            buffer = recibirMSJ();
            System.out.println("jugador " + jugador + ": " + buffer);
            switch (buffer) {
                case "cartas":
                    System.out.println("pidio cartas");
                    System.out.println("ATC Antes de enviar checando si hay datos: " + vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(0).getNombre());
                    System.out.println("ATC Antes de enviar checando si hay datos: " + vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(1).getNombre());
                    enviarMSJ("cartas");
                    enviarCarta(vistaServerJuego1.Servidor_Principal.mazoEnviar);
                    break;
                case "seleccion1":
                    System.out.println("Desactivando Carta 1");
                    vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(0).setActiva(false);
                    break;
                case "seleccion2":
                    System.out.println("Desactivando Carta 2");
                    vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(1).setActiva(false);
                    break;
                case "seleccion3":
                    System.out.println("Desactivando Carta 3");
                    vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(2).setActiva(false);
                    break;
                case "nuevo":
                    System.out.println("Generando Nuevas Cartas");
                    vistaServerJuego1.jbtnSelecCartas.doClick();
                    atenderCliente.currentThread().interrupt();
                    break;
                case "fin":
                    break;
                default:
                    System.out.println("Err");
                    break;
            }
//            if (vistaServerJuego1.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
//                System.out.println("Generando Nuevas Cartas");
////                vistaServerJuego1.jbtnSelecCartas.doClick();
//            }

            if (buffer.equals("fin")) {
                break;
            }
        }
        System.out.println("Termino");
        try {
            // closing resources
            this.entrada.close();
            this.salida.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMSJ(String buffer) {
        try {
            salida.writeUTF(buffer);
        } catch (IOException e) {
            System.out.println("(ENV)Error de entrada/salida.");
        }
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

    private String recibirMSJ() {
        String buffer = "";
        try {
            buffer = entrada.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("(REC)Error de entrada/salida.");
        }
        return buffer;
    }

}
