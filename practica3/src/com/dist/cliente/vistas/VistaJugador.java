/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.cliente.vistas;

import com.dist.cliente.Jugador;
import com.dist.coordinador.Mazo;
import com.dist.sockets.Cliente;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author geoge
 */
public class VistaJugador extends javax.swing.JFrame implements Runnable {
    
    //AUN NO DETECTA CUANDO ENTRA OTRO JUGADOR :'v
    //QUE CLIENTE MANDE MENSAJE A SERVIDOR DE QUE YA PUEDE ENTRAR OTRO JUGADOR ESO SERVIRIA

    Mazo cartasJugador;
    Jugador j;
    Cliente cli;
    boolean activar;
    Thread h1, h2;
    Thread CC;
    int numeroJugador;

    public VistaJugador() {
        initComponents();
        activar = false;
        j = new Jugador();
        h1 = new Thread(this);
        h2 = new Thread(this);
        CC = new Thread(this);
        h1.start();
        h2.start();
        jbtnPeticion.setEnabled(false);
    }

    public void clienteEsperaActivarse() {
        try {
            cli = new Cliente(10000);
            cli.startClientActivar();
            activar = cli.getActivar();
            numeroJugador = cli.getClienteNumero();
            if (activar == true) {
                jbtnPeticion.setEnabled(true);
            }
            System.out.println("Este jugador puede pedir cartas y es el numero " + numeroJugador);
        } catch (IOException ex) {
            Logger.getLogger(VistaJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clientePedirCartas() throws IOException {
        cli = new Cliente();
        cartasJugador = cli.startClient();

//        ExecutorService es = Executors.newCachedThreadPool();
//        es.execute(cli);
        activar = cli.getActivar();
//        cartasJugador = cli.getMazoCliente();
        System.out.println("Activar del jugador es : " + activar);
//        es.shutdown();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePokemon = new javax.swing.JTable();
        jbtnPeticion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTablePokemon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Ataque", "Defensa", "HP"
            }
        ));
        jScrollPane1.setViewportView(jTablePokemon);

        jbtnPeticion.setText("Peticion");
        jbtnPeticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPeticionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Cartas Obtenidas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jbtnPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPeticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPeticionActionPerformed

        try {
            //clienteEsperaActivarse();
            clientePedirCartas();
            jbtnPeticion.setEnabled(false);
            mostrarMazo();
        } catch (Exception ex) {
            Logger.getLogger(VistaJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnPeticionActionPerformed

    public void mostrarMazo() {
        limpiarTabla();
        for (int i = 0; i < cartasJugador.getCincoCartas().size(); i++) {
            addValoresTablaJugador(i);
        }
        j.setMazoCartas(cartasJugador);
        j.guardarMazoBD();
    }

    public void addValoresTablaJugador(int i) {
        DefaultTableModel modelo = (DefaultTableModel) jTablePokemon.getModel();
        Object[] filas = new Object[4];
        filas[0] = cartasJugador.getCincoCartas().get(i).getNombre();
        filas[1] = cartasJugador.getCincoCartas().get(i).getAtaque();
        filas[2] = cartasJugador.getCincoCartas().get(i).getDefensa();
        filas[3] = cartasJugador.getCincoCartas().get(i).getHp();

        modelo.addRow(filas);
        jTablePokemon.setModel(modelo);
    }

    public void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTablePokemon.getModel();
        modelo.setRowCount(0);
        jTablePokemon.setModel(modelo);
    }

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
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePokemon;
    private javax.swing.JButton jbtnPeticion;
    // End of variables declaration//GEN-END:variables

    public void mensajeClientes(){
        try {
            cli = new Cliente(10002);
            cli.saludar(numeroJugador);
            cli.recibirSaludo();
            
            System.out.println("Este jugador puede pedir cartas y es el numero " + numeroJugador);
        } catch (IOException ex) {
            Logger.getLogger(VistaJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        while (h1 == hiloActual) {
            try {
                clienteEsperaActivarse();
                if (activar == true) {
                    break;
                }
                Thread.sleep(50000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VistaJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (h2 == hiloActual) {
            try {
                //clienteEsperaActivarse();
                if (activar == true) {
                    break;
                }
                Thread.sleep(50000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VistaJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
