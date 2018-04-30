/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoge
 */
public class PruebaCli {
    
    public static void main(String[] args) {
        try {
            Cliente c = new Cliente();
            c.startClient();
        } catch (IOException ex) {
            Logger.getLogger(PruebaCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
