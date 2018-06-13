/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import com.dist.DTO.BDCarta;
import com.dist.DTO.BDJugador;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import com.dist.multicast.CliMulticast;
import com.dist.multicast.jPanelPokemonSalvaje;
import javax.swing.JOptionPane;

/**
 *
 * @author geoge
 */
public class Prueba {

    static BDJugador bd1;
    static BDCarta bd;
    static Mazo m;
    static Carta c1, c2, c3;

    public static void main(String[] args) {
//        bd1 = new BDJugador();
//        bd1.guardarJuagador(1, "10.0.0.0", 3030);
//        for (int i = 0; i < 5; i++) {
//            c1 = new Carta();
//            c2 = new Carta();
//            c3 = new Carta();
//
//            c1.getCartaAleatoria();
//            c2.getCartaAleatoria();
//            c3.getCartaAleatoria();
//
//            m = new Mazo();
//            m.addCartasMazo(c1);
//            m.addCartasMazo(c2);
//            m.addCartasMazo(c3);
//
//            bd = new BDCarta(m);
//            bd.guardarMazoServidor(m);
//            System.out.println("Se ha guardad en la BASE :3");
//
//            bd.guardarCartaCliente(1, "10:12:11", c2, 1);
//        }

        Carta c = new Carta();
        Mazo m = new Mazo();
        for (int i = 0; i < m.getTamano(); i++) {
            c = new Carta();
            c.getCartaAleatoria();
            m.addCartasMazo(c);
        }
        c.getCartaAleatoria();
        jPanelPokemonSalvaje panelSalvaje = new jPanelPokemonSalvaje(c, m);
        panelSalvaje.setVisible(true);
        JOptionPane.showConfirmDialog(null, panelSalvaje, c.getNombre(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
