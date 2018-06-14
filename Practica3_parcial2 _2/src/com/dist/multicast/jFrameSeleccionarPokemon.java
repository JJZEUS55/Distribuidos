/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.multicast;

import PClienteJuego.PeleaPokemon;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import static com.dist.multicast.JFramePokemonSalvaje.cartaSalvaje;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author geoge
 */
public class jFrameSeleccionarPokemon extends javax.swing.JFrame implements Runnable {

    private Mazo mazoSeleccion;
    private String pokemonSeleccionado;
    public static Carta auxCartaSeleccion, cartaSalvaje;
    public static boolean ataco = false;
    public static boolean muerto = false;
    public static int idPokemonSelect;
    Map<String, Color> mapcolorTipo;
    private int vidaPokemon;
    private PeleaPokemon pelea;
    private Thread hiloAtaque;
    //Borrar
    int aux = 0;

    /**
     * Creates new form jFrameSeleccionarPokemon
     */
    public jFrameSeleccionarPokemon() {
        initComponents();
//        mapcolorTipo = new HashMap<>();
//
//        addValoresMapColor();
//        jPanelMostrarCartas.setVisible(true);
//        jPanelCarta.setVisible(false);
//        limpiarTabla();
//        mazoSeleccion = new Mazo();
//        for (int i = 0; i < 5; i++) {
//            Carta c = new Carta();
//            c.getCartaAleatoria();
//            mazoSeleccion.addCartasMazo(c);
//            addValoresTabla(c);
//        }
//        hiloAtaque = new Thread(this);
        //hiloAtaque.start();

    }

    public jFrameSeleccionarPokemon(Mazo m, Carta cs) {
        initComponents();
        mapcolorTipo = new HashMap<>();
        addValoresMapColor();
        jPanelMostrarCartas.setVisible(true);
        jPanelCarta.setVisible(false);
        limpiarTabla();
        this.cartaSalvaje = cs;
        this.mazoSeleccion = m;
        for (int i = 0; i < mazoSeleccion.getTamano(); i++) {
            addValoresTabla(mazoSeleccion.getCartas().get(i));
        }
        hiloAtaque = new Thread(this);
        //hiloAtaque.start();
    }

    @Override
    public void run() {
        int vidaTotal = auxCartaSeleccion.getHp();
        Thread hiloActual = Thread.currentThread();
        while(hiloActual == hiloAtaque && !(hiloAtaque.isInterrupted())){
            if(this.ataco == false){
                jBarraVida.setValue(auxCartaSeleccion.getHp());
                if(auxCartaSeleccion.getHp() <= (vidaTotal/2) && auxCartaSeleccion.getHp() > (vidaTotal/4)){
                        jBarraVida.setForeground(new Color(249, 226, 27));
                    }else if(auxCartaSeleccion.getHp() <= (vidaTotal/4)){
                        jBarraVida.setForeground(new Color(237, 28, 36));
                    }
                if(auxCartaSeleccion.getHp() <= 0){
                    JOptionPane.showMessageDialog(this, "Estoy Muerto!!!");
                    JOptionPane.showMessageDialog(this, "No pudiste capturar al pokemon!!!");
                    this.muerto = true;
                    jbtnAtacar.setEnabled(false);
                    hiloAtaque.interrupt();
                }
                if(JFramePokemonSalvaje.capturado == true){
                    JOptionPane.showMessageDialog(this, "Has Obtenido Un Nuevo Pokemon!!!", "CAPTURADO", JOptionPane.DEFAULT_OPTION);
//                    if(){
//                    
//                    }
                    
                    addValoresTabla(cartaSalvaje);
                    jbtnAtacar.setEnabled(false);
                    hiloAtaque.interrupt();
                }
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(jFrameSeleccionarPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) jTablePokemonSelect.getModel();
        model.setRowCount(0);
        jTablePokemonSelect.setModel(model);
    }

    private void addValoresTabla(Carta c) {
        DefaultTableModel modelo = (DefaultTableModel) jTablePokemonSelect.getModel();
        Object[] fila = new Object[5];
        fila[0] = c.getNombre();
        fila[1] = c.getTipo1();
        fila[2] = c.getHp();
        fila[3] = c.getAtaque();
        fila[4] = c.getDefensa();
        modelo.addRow(fila);
        jTablePokemonSelect.setModel(modelo);
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

        jPanelCarta = new javax.swing.JPanel();
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
        jbtnAtacar = new javax.swing.JButton();
        jbtnRetirada = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jBarraVida = new javax.swing.JProgressBar();
        jPanelMostrarCartas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePokemonSelect = new javax.swing.JTable();
        jbtnSeleccionarPokemon = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCarta.setBackground(new java.awt.Color(187, 187, 187));
        jPanelCarta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta.setPreferredSize(new java.awt.Dimension(265, 550));

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
        jLabel28.setText("HP Total    ");

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

        jbtnAtacar.setText("Atacar");
        jbtnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAtacarActionPerformed(evt);
            }
        });

        jbtnRetirada.setText("Retirada");
        jbtnRetirada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRetiradaActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel31.setText("HP    ");

        jBarraVida.setForeground(new java.awt.Color(0, 255, 0));
        jBarraVida.setValue(100);
        jBarraVida.setStringPainted(true);

        javax.swing.GroupLayout jPanelCartaLayout = new javax.swing.GroupLayout(jPanelCarta);
        jPanelCarta.setLayout(jPanelCartaLayout);
        jPanelCartaLayout.setHorizontalGroup(
            jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCartaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCartaLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCartaLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jbtnAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jbtnRetirada)
                .addContainerGap())
            .addGroup(jPanelCartaLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCartaLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBarraVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(18, 18, 18)
                        .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCartaLayout.createSequentialGroup()
                        .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCartaLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCartaLayout.setVerticalGroup(
            jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCartaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jBarraVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanelCartaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAtacar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnRetirada, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanelCarta, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 270, 560));

        jPanelMostrarCartas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Cartas Seleccionadas");
        jPanelMostrarCartas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 6, -1, -1));

        jTablePokemonSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Tipo", "HP", "Ataque", "Title 5"
            }
        ));
        jTablePokemonSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePokemonSelectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePokemonSelect);

        jPanelMostrarCartas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 106, 767, 110));

        jbtnSeleccionarPokemon.setText("Seleccionar");
        jbtnSeleccionarPokemon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSeleccionarPokemonActionPerformed(evt);
            }
        });
        jPanelMostrarCartas.add(jbtnSeleccionarPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 110, 71));

        getContentPane().add(jPanelMostrarCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 310));
        jPanelMostrarCartas.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePokemonSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePokemonSelectMouseClicked
        int columna = jTablePokemonSelect.columnAtPoint(evt.getPoint());
        int fila = jTablePokemonSelect.rowAtPoint(evt.getPoint());
        auxCartaSeleccion = mazoSeleccion.getCartas().get(fila);
        if (columna == 0 && fila >= 0) {
            //JOptionPane.showMessageDialog(this, null, jTablePokemonSelect.getValueAt(fila, columna).toString(), JOptionPane.DEFAULT_OPTION);
            pokemonSeleccionado = jTablePokemonSelect.getValueAt(fila, columna).toString();
            idPokemonSelect = fila;            
            addValoresPanelCarta();
        }
//        }

    }//GEN-LAST:event_jTablePokemonSelectMouseClicked

    private void jbtnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAtacarActionPerformed
        auxCartaSeleccion.Atacar(JFramePokemonSalvaje.cartaSalvaje);
        this.ataco = true;
    }//GEN-LAST:event_jbtnAtacarActionPerformed

    private void jbtnSeleccionarPokemonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSeleccionarPokemonActionPerformed
        if (pokemonSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "No seleccionaste nada, Elije un POKEMON!!!", "ERROR " + pokemonSeleccionado, JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "Se selecciono a " + pokemonSeleccionado, "Pokemon Seleccionado", JOptionPane.DEFAULT_OPTION);
            this.setBounds(800, 300, 270, 580);
            jPanelCarta.setVisible(true);
            jPanelMostrarCartas.setVisible(false);
            hiloAtaque.start();
        }

    }//GEN-LAST:event_jbtnSeleccionarPokemonActionPerformed

    private void jbtnRetiradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRetiradaActionPerformed
        //this.setVisible(false);
//        aux++;
//        if (aux == 1) {
//            vidaPokemon = 50;
//            jBarraVida.setValue(vidaPokemon);
//            jBarraVida.setForeground(new Color(249, 226, 27));
//        }
//        if (aux == 2) {
//            vidaPokemon = 10;
//            jBarraVida.setValue(vidaPokemon);
//            jBarraVida.setForeground(new Color(237, 28, 36));
//        }
        jPanelCarta.setVisible(false);
        jPanelMostrarCartas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbtnRetiradaActionPerformed

    private void addValoresPanelCarta() {
        jLabelImg3.setIcon(auxCartaSeleccion.getIconPokemon());
        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, auxCartaSeleccion.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jtfNombre1.setText(auxCartaSeleccion.getNombre());
        jtfTipo1_1.setText(auxCartaSeleccion.getTipo1());
        jtfTipo2_1.setText(auxCartaSeleccion.getTipo2());
        jtfHP1.setText("" + auxCartaSeleccion.getHp());
        jtfAtaque1.setText("" + auxCartaSeleccion.getAtaque());
        jtfDefensa1.setText("" + auxCartaSeleccion.getDefensa());
        jtfTipo1_1.setBackground(mapcolorTipo.get(auxCartaSeleccion.getTipo1()));
        jtfTipo2_1.setBackground(mapcolorTipo.get(auxCartaSeleccion.getTipo2()));
        jPanelCarta.setBackground(mapcolorTipo.get(auxCartaSeleccion.getTipo1()));
        jBarraVida.setMaximum(auxCartaSeleccion.getHp());
        jBarraVida.setValue(auxCartaSeleccion.getHp());
        vidaPokemon = auxCartaSeleccion.getHp();
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jFrameSeleccionarPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFrameSeleccionarPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFrameSeleccionarPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFrameSeleccionarPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFrameSeleccionarPokemon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jBarraVida;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JPanel jPanelC1;
    private javax.swing.JPanel jPanelCarta;
    private javax.swing.JPanel jPanelMostrarCartas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePokemonSelect;
    private javax.swing.JButton jbtnAtacar;
    private javax.swing.JButton jbtnRetirada;
    private javax.swing.JButton jbtnSeleccionarPokemon;
    private javax.swing.JTextField jtfAtaque1;
    private javax.swing.JTextField jtfDefensa1;
    private javax.swing.JTextField jtfHP1;
    private javax.swing.JTextField jtfNombre1;
    private javax.swing.JTextField jtfTipo1_1;
    private javax.swing.JTextField jtfTipo2_1;
    // End of variables declaration//GEN-END:variables

}
