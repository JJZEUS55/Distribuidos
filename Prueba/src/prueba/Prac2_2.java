/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import com.dist.coordinador.Cartas;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Prac2_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cartas c1 = new Cartas();
        c1.getCartaAleatoria();
        System.out.println(c1.toString());

        Servidor ss;
        try {
            ss = new Servidor();
            ss.startServer(c1);
        } catch (IOException ex) {
            Logger.getLogger(Prac2_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
