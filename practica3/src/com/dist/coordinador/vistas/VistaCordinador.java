/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.coordinador.vistas;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import com.dist.sockets.Servidor;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import servidorC.*;

/**
 *
 * @author geoge
 */
public class VistaCordinador extends javax.swing.JFrame implements Runnable {
    //CORREJIR PORQUE SALE COMO SI ESTUVIERA USANDO LOS SOCKETS DISPOBLES ESO NO DEBERIA PASAR

    Cartas c1, c2, c3;
    Mazo j1, j2, j3;
    int numCartas;
    int numclientes = 0;
    int numClientesActivar = 0;
    int siguienteJugador;
    int contadorClientes = 0;
    AtomicInteger atomics = new AtomicInteger(1);
    int clicks = 0;
    Thread h1, h2, h3;
    Thread HiloCheck, HiloAcceptC;
    Servidor ser, ser2, ser3;
    clase_server CheckServidor;
    InfoPC equipos[] = new InfoPC[5];

    public VistaCordinador() {
        initComponents();
        CheckServidor = new clase_server(3080);
        CheckServidor.iniciar();
        j1 = new Mazo();
        j2 = new Mazo();
        j3 = new Mazo();
        numCartas = 0;
        try {
            ser = new Servidor(10000);
            ser2 = new Servidor();
            ser3 = new Servidor(10202);
        } catch (IOException ex) {
            Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        h1 = new Thread(this);
        h2 = new Thread(this);
        h3 = new Thread(this);
        HiloCheck = new Thread(this);
        HiloAcceptC = new Thread(this);
        h1.start();
        HiloCheck.start();
        HiloAcceptC.start();
    
    }

    /*NOTA LA PARTE mandarSiguienteJugador GENERA UN PROBLEMA DEBIDO A QUE SE MANDA 
    AL MISMO TIEMPO QUE startServerActivaCliente
     */
    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        while (h1 == hiloActual) {
            try {
                numClientesActivar++;
                System.out.println("---------- HILO 1  -------------");
                System.out.println("El cliente a activar es el num " + numClientesActivar);
                ser.startServerActivaCliente(numClientesActivar);

                Thread.sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (h2 == hiloActual) {
            try {
                this.numclientes++;
                System.out.println("--------- HILO 2 -------------");
                if (numclientes == 1) {
                    System.out.println("Mandando mazo a " + numclientes);
                    ser2.startServer(j1);
                    h3.start();
                    //ser2.mandarSiguienteJugador(ser2.getJugadorAIniciar());
                } else if (numclientes == 2) {
                    System.out.println("Mandando mazo a " + numclientes);
                    ser2.startServer(j2);
                    // ser2.mandarSiguienteJugador(ser2.getJugadorAIniciar());
                } else if (numclientes == 3) {
                    System.out.println("Mandando mazo a " + numclientes);
                    ser2.startServer(j3);
                }
                Thread.sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (h3 == hiloActual) {
            System.out.println("--------------- HILO 3 --------------");
            try {
                if (numclientes > 0) {
                    ser3.mandarSiguienteJugador(ser2.getJugadorAIniciar());
                }

                Thread.sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        while(HiloAcceptC == hiloActual)
        { 
            equipos[contadorClientes] = CheckServidor.aceptar(contadorClientes);
            contadorClientes++;
        }
        while(HiloCheck == hiloActual)
        { 
            try {
                Thread.sleep(5000);
                CheckServidor.check(equipos, contadorClientes);
            } catch (Exception ex) {
                Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void get3Cartas() {
        c1 = new Cartas();
        c2 = new Cartas();
        c3 = new Cartas();

        c1.getCartaAleatoria();
        c2.getCartaAleatoria();
        c3.getCartaAleatoria();

        c1.addImagenCarta();
        c2.addImagenCarta();
        c3.addImagenCarta();

        addImagenes();//añade las imagenes de los pokemones a la pantalla
        addInformacionPokemon();//Imprime en pantalla estadisticas de cada carta
        crearMazos();//añade las cartas al mazo para darlas despues a los jugadores
    }

    public void addImagenes() {

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c1.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg1.setIcon(c1.getImagenPokemon());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c2.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg2.setIcon(c2.getImagenPokemon());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c3.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg3.setIcon(c3.getImagenPokemon());
    }

    public void crearMazos() {
        j1.addCartasMazo(c1);
        j2.addCartasMazo(c2);
        j3.addCartasMazo(c3);
        numCartas++;
    }

    public void addInformacionPokemon() {

        //CARTA 1
        jtfNombre1.setText(c1.getNombre());
        jtfTipo1_1.setText(c1.getTipo1());
        jtfTipo2_1.setText(c1.getTipo2());
        jtfHP1.setText("" + c1.getHp());
        jtfAtaque1.setText("" + c1.getAtaque());
        jtfDefensa1.setText("" + c1.getDefensa());

        //CARTA 2
        jtfNombre2.setText(c2.getNombre());
        jtfTipo1_2.setText(c2.getTipo1());
        jtfTipo2_2.setText(c2.getTipo2());
        jtfHP2.setText("" + c2.getHp());
        jtfAtaque2.setText("" + c2.getAtaque());
        jtfDefensa2.setText("" + c2.getDefensa());

        //CARTA 3
        jtfNombre3.setText(c3.getNombre());
        jtfTipo1_3.setText(c3.getTipo1());
        jtfTipo2_3.setText(c3.getTipo2());
        jtfHP3.setText("" + c3.getHp());
        jtfAtaque3.setText("" + c3.getAtaque());
        jtfDefensa3.setText("" + c3.getDefensa());

    }

    public void enviarCartas() {
//        h2 = new Thread(this);
//        h3 = new Thread(this);
//        h2.start();
//        h3.start();
        if (clicks == 0) {
            h2.start();
            //h3.start();
        }

        clicks++;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnTomarCartas = new javax.swing.JButton();
        jPanelCarta2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelImg2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtfDefensa2 = new javax.swing.JTextField();
        jtfNombre2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtfTipo1_2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtfTipo2_2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtfHP2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jtfAtaque2 = new javax.swing.JTextField();
        jPanelCarta3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelImg3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jtfDefensa3 = new javax.swing.JTextField();
        jtfNombre3 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jtfTipo1_3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jtfTipo2_3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jtfHP3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jtfAtaque3 = new javax.swing.JTextField();
        jPanelCarta4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelImg1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jtfDefensa1 = new javax.swing.JTextField();
        jtfNombre1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jtfTipo1_1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jtfTipo2_1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtfHP1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jtfAtaque1 = new javax.swing.JTextField();
        jbtnSelecCartas = new javax.swing.JButton();
        jbtnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbtnTomarCartas.setText("Repartir Mazo");
        jbtnTomarCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTomarCartasActionPerformed(evt);
            }
        });

        jPanelCarta2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta2.setPreferredSize(new java.awt.Dimension(265, 550));

        jLabel2.setText("Carta 2");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabelImg2)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelImg2)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel10.setText("Defensa");

        jtfDefensa2.setText("jTextField1");

        jtfNombre2.setText("jTextField1");

        jLabel11.setText("Tipo 1    ");

        jtfTipo1_2.setText("jTextField1");

        jLabel12.setText("Tipo 2    ");

        jtfTipo2_2.setText("jTextField1");

        jLabel13.setText("HP    ");

        jtfHP2.setText("jTextField1");

        jLabel14.setText("Ataque");

        jLabel15.setText("Nombre");

        jtfAtaque2.setText("jTextField1");

        javax.swing.GroupLayout jPanelCarta2Layout = new javax.swing.GroupLayout(jPanelCarta2);
        jPanelCarta2.setLayout(jPanelCarta2Layout);
        jPanelCarta2Layout.setHorizontalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
            .addGroup(jPanelCarta2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jtfNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTipo1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jtfDefensa2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfAtaque2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfHP2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTipo2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanelCarta2Layout.setVerticalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jtfNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtfTipo1_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtfTipo2_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtfHP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtfAtaque2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtfDefensa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jPanelCarta3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta3.setPreferredSize(new java.awt.Dimension(265, 550));

        jLabel3.setText("Carta 3");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabelImg3)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelImg3)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel16.setText("Defensa");

        jtfDefensa3.setText("jTextField1");

        jtfNombre3.setText("jTextField1");

        jLabel17.setText("Tipo 1    ");

        jtfTipo1_3.setText("jTextField1");

        jLabel18.setText("Tipo 2    ");

        jtfTipo2_3.setText("jTextField1");

        jLabel19.setText("HP    ");

        jtfHP3.setText("jTextField1");

        jLabel20.setText("Ataque");

        jLabel21.setText("Nombre");

        jtfAtaque3.setText("jTextField1");

        javax.swing.GroupLayout jPanelCarta3Layout = new javax.swing.GroupLayout(jPanelCarta3);
        jPanelCarta3.setLayout(jPanelCarta3Layout);
        jPanelCarta3Layout.setHorizontalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jtfNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTipo1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jtfDefensa3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfAtaque3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfHP3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTipo2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanelCarta3Layout.setVerticalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jtfNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jtfTipo1_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jtfTipo2_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jtfHP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jtfAtaque3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jtfDefensa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCarta4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta4.setPreferredSize(new java.awt.Dimension(265, 550));

        jLabel22.setText("Carta 1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabelImg1)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelImg1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel23.setText("Defensa");

        jtfDefensa1.setText("jTextField1");

        jtfNombre1.setText("jTextField1");

        jLabel24.setText("Tipo 1    ");

        jtfTipo1_1.setText("jTextField1");

        jLabel25.setText("Tipo 2    ");

        jtfTipo2_1.setText("jTextField1");

        jLabel26.setText("HP    ");

        jtfHP1.setText("jTextField1");

        jLabel27.setText("Ataque");

        jLabel28.setText("Nombre");

        jtfAtaque1.setText("jTextField1");

        javax.swing.GroupLayout jPanelCarta4Layout = new javax.swing.GroupLayout(jPanelCarta4);
        jPanelCarta4.setLayout(jPanelCarta4Layout);
        jPanelCarta4Layout.setHorizontalGroup(
            jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta4Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
            .addGroup(jPanelCarta4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCarta4Layout.createSequentialGroup()
                        .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanelCarta4Layout.setVerticalGroup(
            jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtnSelecCartas.setText("Seleccionar 3 cartas");
        jbtnSelecCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelecCartasActionPerformed(evt);
            }
        });

        jbtnReporte.setText("Reporte");
        jbtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelCarta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelCarta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanelCarta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnTomarCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jbtnSelecCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCarta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCarta3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCarta4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnTomarCartas, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jbtnSelecCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSelecCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelecCartasActionPerformed
        if (numCartas <= 4) {
            get3Cartas();
        } else {
            try {
                jbtnSelecCartas.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Solo se pueden tener 5 cartas en cada mazo");
            } catch (Exception ex) {
                Logger.getLogger(VistaCordinador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnSelecCartasActionPerformed

    private void jbtnTomarCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTomarCartasActionPerformed

        atomics.addAndGet(1);
        enviarCartas();


    }//GEN-LAST:event_jbtnTomarCartasActionPerformed

    private void jbtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteActionPerformed
        ReporteCordinador rc = new ReporteCordinador(j1, j2, j3);
        rc.setVisible(true);

    }//GEN-LAST:event_jbtnReporteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCordinador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelImg1;
    private javax.swing.JLabel jLabelImg2;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCarta2;
    private javax.swing.JPanel jPanelCarta3;
    private javax.swing.JPanel jPanelCarta4;
    private javax.swing.JButton jbtnReporte;
    private javax.swing.JButton jbtnSelecCartas;
    private javax.swing.JButton jbtnTomarCartas;
    private javax.swing.JTextField jtfAtaque1;
    private javax.swing.JTextField jtfAtaque2;
    private javax.swing.JTextField jtfAtaque3;
    private javax.swing.JTextField jtfDefensa1;
    private javax.swing.JTextField jtfDefensa2;
    private javax.swing.JTextField jtfDefensa3;
    private javax.swing.JTextField jtfHP1;
    private javax.swing.JTextField jtfHP2;
    private javax.swing.JTextField jtfHP3;
    private javax.swing.JTextField jtfNombre1;
    private javax.swing.JTextField jtfNombre2;
    private javax.swing.JTextField jtfNombre3;
    private javax.swing.JTextField jtfTipo1_1;
    private javax.swing.JTextField jtfTipo1_2;
    private javax.swing.JTextField jtfTipo1_3;
    private javax.swing.JTextField jtfTipo2_1;
    private javax.swing.JTextField jtfTipo2_2;
    private javax.swing.JTextField jtfTipo2_3;
    // End of variables declaration//GEN-END:variables

}
