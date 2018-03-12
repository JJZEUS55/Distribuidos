/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reloj.vista;

import com.reloj.logica.Reloj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author geoge
 */
public class Prueba implements Runnable {

    Thread h1;
    Reloj r1 = new Reloj(5, 2, 2);
    
    Prueba(){
        h1 = new Thread(this);
        h1.start();
    }

    public static void main(String[] args) {

        Prueba p1 = new Prueba();
    }

    @Override
    public void run() {
        while (true) {
            r1.pasarTiempo();
            r1.imprimeHora();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

}
