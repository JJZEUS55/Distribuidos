/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PClienteJuego;

import com.dist.juego.Carta;

/**
 *
 * @author geoge
 */
public class JPanelCartaTabla extends javax.swing.JPanel {

    /**
     * Creates new form JPanelCartaTabla
     */
    public JPanelCartaTabla() {
        initComponents();
    }

    public JPanelCartaTabla(Carta c) {
        initComponents();
        jLabelImg3.setIcon(c.getIconPokemon());
        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jtfNombre1.setText(c.getNombre());
        jtfTipo1_1.setText(c.getTipo1());
        jtfTipo2_1.setText(c.getTipo2());
        jtfHP1.setText("" + c.getHp());
        jtfAtaque1.setText("" + c.getAtaque());
        jtfDefensa1.setText("" + c.getDefensa());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel24 = new javax.swing.JLabel();
        jPanelC1 = new javax.swing.JPanel();
        jLabelImg3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtfDefensa1 = new javax.swing.JTextField();
        jtfNombre1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jtfTipo1_1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jtfTipo2_1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jtfHP1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jtfAtaque1 = new javax.swing.JTextField();

        jLabel24.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jLabel24.setText("Carta 1");

        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 36), new java.awt.Color(255, 51, 0)), "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION)); // NOI18N
        jPanelC1.setPreferredSize(new java.awt.Dimension(96, 96));

        jLabelImg3.setPreferredSize(new java.awt.Dimension(96, 96));

        javax.swing.GroupLayout jPanelC1Layout = new javax.swing.GroupLayout(jPanelC1);
        jPanelC1.setLayout(jPanelC1Layout);
        jPanelC1Layout.setHorizontalGroup(
            jPanelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanelC1Layout.setVerticalGroup(
            jPanelC1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC1Layout.createSequentialGroup()
                .addComponent(jLabelImg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Defensa");

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
        jLabel28.setText("HP    ");

        jtfHP1.setBackground(new java.awt.Color(0, 230, 0));
        jtfHP1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfHP1.setText("jTextField1");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel29.setText("Ataque");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel30.setText("Nombre");

        jtfAtaque1.setBackground(new java.awt.Color(226, 37, 43));
        jtfAtaque1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfAtaque1.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JPanel jPanelC1;
    private javax.swing.JTextField jtfAtaque1;
    private javax.swing.JTextField jtfDefensa1;
    private javax.swing.JTextField jtfHP1;
    private javax.swing.JTextField jtfNombre1;
    private javax.swing.JTextField jtfTipo1_1;
    private javax.swing.JTextField jtfTipo2_1;
    // End of variables declaration//GEN-END:variables
}
