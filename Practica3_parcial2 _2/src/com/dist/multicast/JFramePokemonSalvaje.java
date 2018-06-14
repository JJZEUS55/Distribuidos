/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.multicast;

import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author geoge
 */
public class JFramePokemonSalvaje extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form JFramePokemonSalvaje
     */
    Map<String, Color> mapcolorTipo;
    Mazo mazoCliente;
    public static Carta cartaSalvaje;
    private Thread hiloAtaque;
    public static boolean capturado = false;

    //Borrar al terminar
    public JFramePokemonSalvaje() {
        initComponents();
//        Carta c = new Carta();
//        c.getCartaAleatoria();
//        mapcolorTipo = new HashMap<>();
//        this.cartaSalvaje = c;
//        addValoresMapColor();
//        jLabelImg3.setIcon(c.getIconPokemon());
//        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
//        jtfNombre1.setText(c.getNombre());
//        jtfTipo1_1.setText(c.getTipo1());
//        jtfTipo2_1.setText(c.getTipo2());
//        jtfHP1.setText("" + c.getHp());
//        jtfAtaque1.setText("" + c.getAtaque());
//        jtfDefensa1.setText("" + c.getDefensa());
//        jtfTipo1_1.setBackground(mapcolorTipo.get(c.getTipo1()));
//        jtfTipo2_1.setBackground(mapcolorTipo.get(c.getTipo2()));
//        jPanelCarta.setBackground(mapcolorTipo.get(c.getTipo1()));
//        jBarVida.setMaximum(c.getHp());
//        jBarVida.setValue(c.getHp());
//        hiloAtaque = new Thread(this);
//        hiloAtaque.start();
    }

    public JFramePokemonSalvaje(Carta c, Mazo m) {
        initComponents();
        this.mazoCliente = m;
        this.cartaSalvaje = c;
        mapcolorTipo = new HashMap<>();
        addValoresMapColor();
        jLabelImg3.setIcon(c.getIconPokemon());
        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jtfNombre1.setText(c.getNombre());
        jtfTipo1_1.setText(c.getTipo1());
        jtfTipo2_1.setText(c.getTipo2());
        jtfHP1.setText("" + c.getHp());
        jtfAtaque1.setText("" + c.getAtaque());
        jtfDefensa1.setText("" + c.getDefensa());
        jtfTipo1_1.setBackground(mapcolorTipo.get(c.getTipo1()));
        jtfTipo2_1.setBackground(mapcolorTipo.get(c.getTipo2()));
        jPanelCarta.setBackground(mapcolorTipo.get(c.getTipo1()));
        jBarVida.setMaximum(c.getHp());
        jBarVida.setValue(c.getHp());
        hiloAtaque = new Thread(this);
        hiloAtaque.start();
    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();
        int vidaTotal = cartaSalvaje.getHp();
        while (hiloActual == hiloAtaque && !(hiloAtaque.isInterrupted())) {
            if (jFrameSeleccionarPokemon.ataco == true) {
                if (cartaSalvaje.getHp() > 0) {
                    System.out.println("Entre en el ATACO");
                    jBarVida.setValue(cartaSalvaje.getHp());
                    if(cartaSalvaje.getHp() <= (vidaTotal/2) && cartaSalvaje.getHp() > (vidaTotal/4)){
                        jBarVida.setForeground(new Color(249, 226, 27));
                    }else if(cartaSalvaje.getHp() <= (vidaTotal/4)){
                        jBarVida.setForeground(new Color(237, 28, 36));
                    }
                    
                    cartaSalvaje.Atacar(jFrameSeleccionarPokemon.auxCartaSeleccion);
                    jFrameSeleccionarPokemon.ataco = false;
                    JOptionPane.showMessageDialog(this, "Acabo de atacar el salvaje");
                }else{
                     jBarVida.setValue(cartaSalvaje.getHp());
                     JOptionPane.showMessageDialog(this, "Estoy Muerto!!!");
                     jFrameSeleccionarPokemon.ataco = false;
                     cartaSalvaje.setHP(vidaTotal);
                     this.capturado = true;
                     hiloAtaque.interrupt();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFramePokemonSalvaje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    private void addValoresMapColor() {
        mapcolorTipo.put("bug", new Color(168, 185, 31));//Cambiar a cafe
        mapcolorTipo.put("dragon", new Color(112, 56, 248));
        mapcolorTipo.put("electric", new Color(248, 208, 48));
        mapcolorTipo.put("fairy", new Color(182, 165, 130));
        mapcolorTipo.put("fighting", new Color(193, 48, 40));// Buscar
        mapcolorTipo.put("fire", new Color(240, 128, 48));
        mapcolorTipo.put("flying", new Color(168, 163, 145));
        mapcolorTipo.put("grass", new Color(120, 200, 80));
        mapcolorTipo.put("ground", new Color(192, 166, 65));//Buscar
        mapcolorTipo.put("ice", new Color(113, 158, 235));
        mapcolorTipo.put("normal", new Color(168, 168, 120));//
        mapcolorTipo.put("poison", new Color(160, 64, 160));//Buscar
        mapcolorTipo.put("psychic", new Color(248, 88, 136));
        mapcolorTipo.put("rock", new Color(216, 186, 93));
        mapcolorTipo.put("steel", new Color(235, 203, 80));
        mapcolorTipo.put("water", new Color(104, 144, 240));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanelCarta = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jtfHP1 = new javax.swing.JTextField();
        jPanelC1 = new javax.swing.JPanel();
        jLabelImg3 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtfAtaque1 = new javax.swing.JTextField();
        jtfDefensa1 = new javax.swing.JTextField();
        jtfNombre1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtfTipo1_1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jtfTipo2_1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jbtnCapturar = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jBarVida = new javax.swing.JProgressBar();

        jButton1.setText("jButton1");

        jLabel24.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jLabel24.setText("Pokemon Salvaje");

        jtfHP1.setBackground(new java.awt.Color(0, 230, 0));
        jtfHP1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfHP1.setText("jTextField1");
        jtfHP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfHP1ActionPerformed(evt);
            }
        });

        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 36), new java.awt.Color(255, 51, 0)), "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N
        jPanelC1.setPreferredSize(new java.awt.Dimension(96, 96));

        jLabelImg3.setPreferredSize(new java.awt.Dimension(96, 96));

        javax.swing.GroupLayout jPanelC1Layout = new javax.swing.GroupLayout(jPanelC1);
        jPanelC1.setLayout(jPanelC1Layout);
        jPanelC1Layout.setHorizontalGroup(
            jPanelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelC1Layout.setVerticalGroup(
            jPanelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC1Layout.createSequentialGroup()
                .addComponent(jLabelImg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("Ataque");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setText("Nombre");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Defensa");

        jtfAtaque1.setBackground(new java.awt.Color(226, 37, 43));
        jtfAtaque1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfAtaque1.setText("jTextField1");

        jtfDefensa1.setBackground(new java.awt.Color(85, 172, 238));
        jtfDefensa1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfDefensa1.setText("jTextField1");

        jtfNombre1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfNombre1.setText("jTextField1");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel26.setText("Tipo 1    ");

        jtfTipo1_1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo1_1.setText("jTextField1");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel27.setText("Tipo 2    ");

        jtfTipo2_1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo2_1.setText("jTextField1");

        jLabel28.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel28.setText("HP  Total  ");

        jbtnCapturar.setText("Capturar");
        jbtnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCapturarActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("HP   ");

        jBarVida.setForeground(new java.awt.Color(0, 204, 0));
        jBarVida.setValue(100);
        jBarVida.setStringPainted(true);

        javax.swing.GroupLayout jPanelCartaLayout = new javax.swing.GroupLayout(jPanelCarta);
        jPanelCarta.setLayout(jPanelCartaLayout);
        jPanelCartaLayout.setHorizontalGroup(
            jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCartaLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCartaLayout.createSequentialGroup()
                        .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCartaLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCartaLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCartaLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCartaLayout.createSequentialGroup()
                                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29))
                                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCartaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))
                                    .addGroup(jPanelCartaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(38, 38, 38))
                    .addGroup(jPanelCartaLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCartaLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jbtnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBarVida, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCartaLayout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel24)
                        .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(44, Short.MAX_VALUE)))
        );
        jPanelCartaLayout.setVerticalGroup(
            jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCartaLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jBarVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jbtnCapturar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCartaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel24)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(337, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelCarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfHP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfHP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfHP1ActionPerformed

    private void jbtnCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCapturarActionPerformed

//        mazoCliente = new Mazo();

//        for (int i = 0; i < 5; i++) {
//            Carta c2 = new Carta();
//            c2.getCartaAleatoria();
//            mazoCliente.addCartasMazo(c2);
//        }
        jFrameSeleccionarPokemon seleccion = new jFrameSeleccionarPokemon(mazoCliente, cartaSalvaje);
        seleccion.setVisible(true);
        jbtnCapturar.setEnabled(false);

    }//GEN-LAST:event_jbtnCapturarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFramePokemonSalvaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePokemonSalvaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePokemonSalvaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePokemonSalvaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePokemonSalvaje().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jBarVida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelC1;
    private javax.swing.JPanel jPanelCarta;
    private javax.swing.JButton jbtnCapturar;
    private javax.swing.JTextField jtfAtaque1;
    private javax.swing.JTextField jtfDefensa1;
    private javax.swing.JTextField jtfHP1;
    private javax.swing.JTextField jtfNombre1;
    private javax.swing.JTextField jtfTipo1_1;
    private javax.swing.JTextField jtfTipo2_1;
    // End of variables declaration//GEN-END:variables

}
