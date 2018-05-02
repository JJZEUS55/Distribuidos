/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prac2_2;

import com.dist.sockets.Servidor;
import com.dist.coordinador.Cartas;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class Prac2_2 implements Serializable{
    
    String hola = "hola";
    int num = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        Cartas c1 = new Cartas();
        c1.getCartaAleatoria();
        System.out.println(c1.toString());
        
        Prac2_2 p2 = new Prac2_2();
        
        //System.out.println(InetAddress.getLocalHost().getHostAddress());

        
        
        

    }

}
