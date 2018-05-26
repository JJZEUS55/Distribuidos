/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PClienteJuego;

import java.net.InetAddress;
import token.tokenCliente;
import token.tokenServer;


public class vistaClienteJuego extends javax.swing.JFrame implements Runnable {
    tokenServer Servidor; // tokenserver y cliente se encargan solamente de los procesos relacionados al token
    tokenCliente Cliente;
    ClienteJuego Cliente_Principal;
    Thread HiloEsperaToken; //Hilo del servidor para esperar el token
    Thread HiloEsperaConTok; //Hilo para la espera de la conexion, necesario para evitar el bloqueo del programa 
    
    
    Boolean token = false;
    int Njugador;
    int prioridad;
    String buffer;


    public vistaClienteJuego() 
    {
        initComponents();
        setTitle("Jugador");
        setLocationRelativeTo(null);
        setVisible(true);
        jButton_token.setEnabled(false);
        HiloEsperaToken = new Thread(this);
        HiloEsperaConTok = new Thread(this);
        
    }
    
    @Override
    public void run() 
    {
        Thread hilo = Thread.currentThread();
        while(hilo == HiloEsperaConTok) // Este hilo se puede parar por completo tan pronto acepta ya que solo requiere la primer conexion
        {
            Servidor.acceptar();
            System.out.println("...");
            HiloEsperaToken.start();        
        }
        while(hilo == HiloEsperaToken)
        {
            buffer = Servidor.EsperarMensaje();           
            jButton_token.setEnabled(Servidor.isToken());
            if(!buffer.equals("Token"))
                Cliente.accion(buffer);           
        }
        
    }
    
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        siguiente_ip = new javax.swing.JTextField();
        siguiente_puerto = new javax.swing.JTextField();
        PuertoPropio = new javax.swing.JTextField();
        jLabelinfo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jButton_token = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jugador = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_prioridad = new javax.swing.JTextField();
        jButton_pruebaNS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP");

        jLabel2.setText("Puerto");

        jLabel3.setText("Info jugador siguiente");

        siguiente_ip.setText("localhost");
        siguiente_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguiente_ipActionPerformed(evt);
            }
        });

        siguiente_puerto.setText("300");

        PuertoPropio.setText("300");

        jLabelinfo.setText("Info propia");

        jLabel4.setText("Puerto");

        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jButton_token.setText("Token");
        jButton_token.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tokenActionPerformed(evt);
            }
        });

        jLabel5.setText("Jugador");

        jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugadorActionPerformed(evt);
            }
        });

        jLabel6.setText("Prioridad");

        jButton_pruebaNS.setText("Iniciar Busqueda");
        jButton_pruebaNS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pruebaNSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addComponent(jButton_token, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabelinfo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jButtonIniciar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(PuertoPropio, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(jugador)
                                    .addComponent(jTextField_prioridad))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(siguiente_puerto)
                                    .addComponent(siguiente_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)))))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButton_pruebaNS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelinfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(siguiente_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PuertoPropio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(siguiente_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField_prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jButtonIniciar)
                .addGap(13, 13, 13)
                .addComponent(jButton_token)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_pruebaNS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        Servidor = new tokenServer(Integer.valueOf(PuertoPropio.getText())); //servidor para token y caida del server
        Cliente_Principal = new ClienteJuego("localhost", 3000); //Puerto para servidor y cliente principal 3000
        Servidor.iniciar();
        Cliente_Principal.iniciar(PuertoPropio.getText());
        Njugador = Cliente_Principal.getJugador();
        Cliente = new tokenCliente(Cliente_Principal.getIP_siguiente(), Cliente_Principal.getPuerto_siguiente());
        Cliente.setPrioridad(prioridad);
        

        
        if(Njugador == 1){
            token = true;
        }
        jButton_token.setEnabled(token);
        HiloEsperaConTok.start();
        
        jButtonIniciar.setEnabled(false);
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButton_tokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tokenActionPerformed
        Cliente.enviarToken();
        jButton_token.setEnabled(false);
    }//GEN-LAST:event_jButton_tokenActionPerformed

    private void jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jugadorActionPerformed

    private void siguiente_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguiente_ipActionPerformed

    private void jButton_pruebaNSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pruebaNSActionPerformed
        System.out.println("Nuevoserver");
        Cliente.enviarMSJ(jTextField_prioridad.getText());
    }//GEN-LAST:event_jButton_pruebaNSActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vistaClienteJuego().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PuertoPropio;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButton_pruebaNS;
    private javax.swing.JButton jButton_token;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelinfo;
    private javax.swing.JTextField jTextField_prioridad;
    private javax.swing.JTextField jugador;
    private javax.swing.JTextField siguiente_ip;
    private javax.swing.JTextField siguiente_puerto;
    // End of variables declaration//GEN-END:variables
}
