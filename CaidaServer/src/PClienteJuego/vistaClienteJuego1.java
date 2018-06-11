package PClienteJuego;

import static PClienteJuego.vistaClienteJuego1.rel;
import PServerJuego.vistaServerJuego1;
import Reloj.reloj;
import com.dist.DTO.BDCarta;
import com.dist.DTO.ServidoresNom;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import token.tokenCliente;
import token.tokenServer;

public class vistaClienteJuego1 extends javax.swing.JFrame implements Runnable {

    tokenServer Servidor; // tokenserver y cliente se encargan solamente de los procesos relacionados al token
    tokenCliente Cliente;
    ClienteJuego Cliente_Principal;
    Thread HiloEsperaToken; //Hilo del servidor para esperar el token
    Thread HiloEsperaConTok; //Hilo para la espera de la conexion, necesario para evitar el bloqueo del programa   
    Thread HiloesperarMensajeSP; //Hilo de espera de mensajes del servidor principal del juego 
    Thread HiloLamport;
    Boolean funcionamiento = false;
    static reloj rel = new reloj();
    int prioridad;
    int jugador = 0;
    Mazo mazoRecibido, mazoCliente;
    ButtonGroup grupoRB;
    Map<String, Color> mapcolorTipo;

    public vistaClienteJuego1() {
        initComponents();
        setTitle("Jugador");
        setLocationRelativeTo(null);
        setVisible(true);
        mazoCliente = new Mazo();

        grupoRB = new ButtonGroup();
        mapcolorTipo = new HashMap<String, Color>();
        grupoRB.add(jrbCarta1);
        grupoRB.add(jrbCarta2);
        grupoRB.add(jrbCarta3);
        this.jrbCarta1.setActionCommand("seleccion1");
        this.jrbCarta2.setActionCommand("seleccion2");
        this.jrbCarta3.setActionCommand("seleccion3");

        jButton_token.setEnabled(false);
        HiloEsperaConTok = new Thread(this);
        HiloesperarMensajeSP = new Thread(this);
        HiloEsperaToken = new Thread(this);
        HiloLamport = new Thread(this);
        HiloLamport.start();

        addValoresMapColor();
        limpiarTabla();

    }

    @Override
    public void run() {
        String buffer;
        int estado_mensajes;
        boolean bandera = false;
        Thread hilo = Thread.currentThread();
        while (hilo == HiloEsperaConTok) // Este hilo se puede parar por completo tan pronto acepta ya que solo requiere la primer conexion
        {
            Servidor.acceptar();
            System.out.println("...");
            if (bandera == false) {
                System.out.println("Iniciando Hilo");
                HiloEsperaToken.start();
                bandera = true;
            } else {
                HiloEsperaToken.interrupt();
                HiloEsperaToken = new Thread(this);
                HiloEsperaToken.start();
                System.out.println("Reiniciando hilo");
            }
        }
        while (hilo == HiloEsperaToken && !(HiloEsperaToken.isInterrupted())) // este hilo es para recibir los mensajes que se mandan entre los jugadores
        {
            buffer = Servidor.EsperarMensaje();
            jButton_token.setEnabled(Servidor.isToken());
            jButton_PedirCartas.setEnabled(Servidor.isToken());
            if (!buffer.equals("Token")) {
                Cliente.accion(buffer);
            }
            if (Servidor.isElegido())// se checan banderas dentro de las clases token para iniciar el nuevo servidor en uno de los jugadores 
            {
                System.out.println("Iniciando nuevo servidor......");
                vistaServerJuego1 n = new vistaServerJuego1(Cliente_Principal.getJugador(), Servidor.isToken());
                n.setVisible(true);
                this.setVisible(false);
                HiloEsperaToken.interrupt();
            } else if (Cliente.isCancelarReenvio())// o conectarse al nuevo servidor segun sea el caso
            {
                System.out.println("Conectando al nuevo servidor(" + Servidor.getIPNuevoServer() + ")...");
                Cliente_Principal = new ClienteJuego(Servidor.getIPNuevoServer(), 3000, jugador);
                Cliente_Principal.iniciar(PuertoPropio.getText());
                HiloesperarMensajeSP = new Thread(this);
                HiloesperarMensajeSP.start();
            }
        }
        while (hilo == HiloesperarMensajeSP && !(HiloesperarMensajeSP.isInterrupted())) {
            estado_mensajes = Cliente_Principal.IterprestarMensaje();
            if (estado_mensajes == 1) {
                Cliente = new tokenCliente(Cliente_Principal.getIP_siguiente(), Cliente_Principal.getPuerto_siguiente());
                Cliente.setPrioridad(prioridad);
                Servidor.setPrioridad(prioridad);
                if (Cliente_Principal.getJugador() == 1 && funcionamiento == false) {
                    Servidor.setToken(true);
                }
                jButton_token.setEnabled(Servidor.isToken());
                jButton_PedirCartas.setEnabled(Servidor.isToken());
                funcionamiento = true;
            } else if (estado_mensajes == 2) {
                Servidor.setToken(true);
                jButton_token.setEnabled(Servidor.isToken());
                jButton_PedirCartas.setEnabled(Servidor.isToken());
            } else if (estado_mensajes == 0) {
                System.out.println("El servidor murio");
                Cliente.enviarMSJ(jTextField_prioridad.getText());
                HiloesperarMensajeSP.interrupt();
            }
        }

        while (hilo == HiloLamport) {
            rel.pasarTiempo();
            jLabel_Reloj.setText(rel.imprimeHora());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMostrarCartas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePokemonSelect = new javax.swing.JTable();
        jFondoMostrarCartas = new javax.swing.JLabel();
        jPanel_inicio = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_prioridad = new javax.swing.JTextField();
        PuertoPropio = new javax.swing.JTextField();
        jLabelinfo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3Cartas = new javax.swing.JPanel();
        jPanelCarta1 = new javax.swing.JPanel();
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
        jrbCarta1 = new javax.swing.JRadioButton();
        jPanelCarta2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelC2 = new javax.swing.JPanel();
        jLabelImg4 = new javax.swing.JLabel();
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
        jrbCarta2 = new javax.swing.JRadioButton();
        jPanelCarta3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelC3 = new javax.swing.JPanel();
        jLabelImg5 = new javax.swing.JLabel();
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
        jrbCarta3 = new javax.swing.JRadioButton();
        jLabelFondoCartas = new javax.swing.JLabel();
        jButton_PedirCartas = new javax.swing.JButton();
        jButton_token = new javax.swing.JButton();
        jLabel_Reloj = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelMostrarCartas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Cartas Seleccionadas");
        jPanelMostrarCartas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(337, 6, -1, -1));

        jTablePokemonSelect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Tipo", "HP", "Ataque", "Defensa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePokemonSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePokemonSelectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePokemonSelect);

        jPanelMostrarCartas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 99, 767, 203));

        jFondoMostrarCartas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jFondoMostrarCartas.setText("jLabel9");
        jPanelMostrarCartas.add(jFondoMostrarCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 850, 530));

        getContentPane().add(jPanelMostrarCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 850, 520));
        jPanelMostrarCartas.setVisible(false);

        jPanel_inicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Prioridad");
        jPanel_inicio.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        jTextField_prioridad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel_inicio.add(jTextField_prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 55, -1));

        PuertoPropio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        PuertoPropio.setText("300");
        jPanel_inicio.add(PuertoPropio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 55, -1));

        jLabelinfo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelinfo.setText("Información Propia");
        jPanel_inicio.add(jLabelinfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Puerto");
        jPanel_inicio.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        jButtonIniciar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });
        jPanel_inicio.add(jButtonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 110, 50));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Jugador");
        jPanel_inicio.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel_inicio.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 550));

        getContentPane().add(jPanel_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 850, 550));

        jPanel3Cartas.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3Cartas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCarta1.setBackground(new java.awt.Color(255, 204, 51));
        jPanelCarta1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta1.setPreferredSize(new java.awt.Dimension(265, 550));

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

        jrbCarta1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jrbCarta1.setText("Seleccionar Carta 1");

        javax.swing.GroupLayout jPanelCarta1Layout = new javax.swing.GroupLayout(jPanelCarta1);
        jPanelCarta1.setLayout(jPanelCarta1Layout);
        jPanelCarta1Layout.setHorizontalGroup(
            jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCarta1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCarta1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jrbCarta1)
                    .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelCarta1Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(18, 18, 18)
                            .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelCarta1Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(18, 18, 18)
                            .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelCarta1Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addGap(18, 18, 18)
                            .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelCarta1Layout.createSequentialGroup()
                            .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel28)
                                .addComponent(jLabel29))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanelCarta1Layout.setVerticalGroup(
            jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jtfNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtfTipo1_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtfHP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jtfAtaque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtfDefensa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jrbCarta1)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 470));

        jPanelCarta2.setBackground(new java.awt.Color(102, 102, 255));
        jPanelCarta2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta2.setPreferredSize(new java.awt.Dimension(265, 550));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jLabel2.setText("Carta 2");

        jPanelC2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanelC2.setPreferredSize(new java.awt.Dimension(120, 120));

        jLabelImg4.setPreferredSize(new java.awt.Dimension(96, 96));

        javax.swing.GroupLayout jPanelC2Layout = new javax.swing.GroupLayout(jPanelC2);
        jPanelC2.setLayout(jPanelC2Layout);
        jPanelC2Layout.setHorizontalGroup(
            jPanelC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanelC2Layout.setVerticalGroup(
            jPanelC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC2Layout.createSequentialGroup()
                .addComponent(jLabelImg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Defensa");

        jtfDefensa2.setBackground(new java.awt.Color(85, 172, 238));
        jtfDefensa2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfDefensa2.setText("jTextField1");

        jtfNombre2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfNombre2.setText("jTextField1");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Tipo 1    ");

        jtfTipo1_2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo1_2.setText("jTextField1");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Tipo 2    ");

        jtfTipo2_2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo2_2.setText("jTextField1");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("HP    ");

        jtfHP2.setBackground(new java.awt.Color(0, 230, 0));
        jtfHP2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfHP2.setText("jTextField1");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Ataque");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Nombre");

        jtfAtaque2.setBackground(new java.awt.Color(226, 37, 43));
        jtfAtaque2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfAtaque2.setText("jTextField1");

        jrbCarta2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jrbCarta2.setText("Seleccionar Carta 2");

        javax.swing.GroupLayout jPanelCarta2Layout = new javax.swing.GroupLayout(jPanelCarta2);
        jPanelCarta2.setLayout(jPanelCarta2Layout);
        jPanelCarta2Layout.setHorizontalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(108, 108, 108))
            .addGroup(jPanelCarta2Layout.createSequentialGroup()
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
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
                                    .addComponent(jtfTipo2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jrbCarta2)))
                    .addGroup(jPanelCarta2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanelC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCarta2Layout.setVerticalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jrbCarta2)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 6, -1, 470));

        jPanelCarta3.setBackground(new java.awt.Color(51, 255, 0));
        jPanelCarta3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCarta3.setPreferredSize(new java.awt.Dimension(265, 550));

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jLabel3.setText("Carta 3");

        jPanelC3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemon1", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanelC3.setPreferredSize(new java.awt.Dimension(120, 120));

        jLabelImg5.setPreferredSize(new java.awt.Dimension(96, 96));

        javax.swing.GroupLayout jPanelC3Layout = new javax.swing.GroupLayout(jPanelC3);
        jPanelC3.setLayout(jPanelC3Layout);
        jPanelC3Layout.setHorizontalGroup(
            jPanelC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImg5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanelC3Layout.setVerticalGroup(
            jPanelC3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelC3Layout.createSequentialGroup()
                .addComponent(jLabelImg5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Defensa");

        jtfDefensa3.setBackground(new java.awt.Color(85, 172, 238));
        jtfDefensa3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfDefensa3.setText("jTextField1");

        jtfNombre3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfNombre3.setText("jTextField1");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Tipo 1    ");

        jtfTipo1_3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo1_3.setText("jTextField1");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Tipo 2    ");

        jtfTipo2_3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfTipo2_3.setText("jTextField1");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("HP    ");

        jtfHP3.setBackground(new java.awt.Color(0, 230, 0));
        jtfHP3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfHP3.setText("jTextField1");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Ataque");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Nombre");

        jtfAtaque3.setBackground(new java.awt.Color(226, 37, 43));
        jtfAtaque3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfAtaque3.setText("jTextField1");

        jrbCarta3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jrbCarta3.setText("Seleccionar Carta 3");

        javax.swing.GroupLayout jPanelCarta3Layout = new javax.swing.GroupLayout(jPanelCarta3);
        jPanelCarta3.setLayout(jPanelCarta3Layout);
        jPanelCarta3Layout.setHorizontalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel3))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
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
                                    .addComponent(jtfTipo2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jrbCarta3)))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanelC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanelCarta3Layout.setVerticalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jrbCarta3)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 6, -1, 470));

        jLabelFondoCartas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jLabelFondoCartas.setText("jLabel1");
        jLabelFondoCartas.setPreferredSize(new java.awt.Dimension(843, 562));
        jPanel3Cartas.add(jLabelFondoCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 843, 560));

        getContentPane().add(jPanel3Cartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel3Cartas.setVisible(false);

        jButton_PedirCartas.setText("Pedir Cartas");
        jButton_PedirCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PedirCartasActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_PedirCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 560, -1, 60));

        jButton_token.setText("Seleccionar carta");
        jButton_token.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tokenActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_token, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 560, -1, 60));

        jLabel_Reloj.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_Reloj.setText("Reloj");
        getContentPane().add(jLabel_Reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 850, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showInformacionCartas() {
        boolean activaC1 = mazoRecibido.getCartas().get(0).isActiva();
        boolean activaC2 = mazoRecibido.getCartas().get(1).isActiva();
        boolean activaC3 = mazoRecibido.getCartas().get(2).isActiva();
        System.out.println("Informacion " + activaC1);
        Component[] c = null;
        //CARTA 1
        jtfNombre1.setText(mazoRecibido.getCartas().get(0).getNombre());
        jtfTipo1_1.setText(mazoRecibido.getCartas().get(0).getTipo1());
        jtfTipo2_1.setText(mazoRecibido.getCartas().get(0).getTipo2());
        jtfHP1.setText("" + mazoRecibido.getCartas().get(0).getHp());
        jtfAtaque1.setText("" + mazoRecibido.getCartas().get(0).getAtaque());
        jtfDefensa1.setText("" + mazoRecibido.getCartas().get(0).getDefensa());
        //Cambiando colores
        jtfTipo1_1.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(0).getTipo1()));
        jtfTipo2_1.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(0).getTipo2()));
        jPanelCarta1.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(0).getTipo1()));

        c = jPanelCarta1.getComponents();
        for (Component component : c) {
            component.setEnabled(activaC1);
        }

        //CARTA 2
        jtfNombre2.setText(mazoRecibido.getCartas().get(1).getNombre());
        jtfTipo1_2.setText(mazoRecibido.getCartas().get(1).getTipo1());
        jtfTipo2_2.setText(mazoRecibido.getCartas().get(1).getTipo2());
        jtfHP2.setText("" + mazoRecibido.getCartas().get(1).getHp());
        jtfAtaque2.setText("" + mazoRecibido.getCartas().get(1).getAtaque());
        jtfDefensa2.setText("" + mazoRecibido.getCartas().get(1).getDefensa());
        jtfTipo1_2.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(1).getTipo1()));
        jtfTipo2_2.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(1).getTipo2()));
        jPanelCarta2.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(1).getTipo1()));

        c = jPanelCarta2.getComponents();
        for (Component component : c) {
            component.setEnabled(activaC2);
        }

        //CARTA 3
        jtfNombre3.setText(mazoRecibido.getCartas().get(2).getNombre());
        jtfTipo1_3.setText(mazoRecibido.getCartas().get(2).getTipo1());
        jtfTipo2_3.setText(mazoRecibido.getCartas().get(2).getTipo2());
        jtfHP3.setText("" + mazoRecibido.getCartas().get(2).getHp());
        jtfAtaque3.setText("" + mazoRecibido.getCartas().get(2).getAtaque());
        jtfDefensa3.setText("" + mazoRecibido.getCartas().get(2).getDefensa());
        jtfTipo1_3.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(2).getTipo1()));
        jtfTipo2_3.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(2).getTipo2()));
        jPanelCarta3.setBackground(mapcolorTipo.get(mazoRecibido.getCartas().get(2).getTipo1()));

        c = jPanelCarta3.getComponents();
        for (Component component : c) {
            component.setEnabled(activaC3);
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

    public void addImagenesCarta() {

        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, mazoRecibido.getCartas().get(0).getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg3.setIcon(mazoRecibido.getCartas().get(0).getIconPokemon());

        jPanelC2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, mazoRecibido.getCartas().get(1).getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg4.setIcon(mazoRecibido.getCartas().get(1).getIconPokemon());

        jPanelC3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, mazoRecibido.getCartas().get(2).getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg5.setIcon(mazoRecibido.getCartas().get(2).getIconPokemon());
    }

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        Servidor = new tokenServer(Integer.valueOf(PuertoPropio.getText())); //servidor para token y caida del server
        Cliente_Principal = new ClienteJuego("localhost", 3000, jugador); //Puerto para servidor y cliente principal 3000
        Servidor.iniciar();
        Cliente_Principal.iniciar(PuertoPropio.getText());
        prioridad = Integer.valueOf(jTextField_prioridad.getText());
        HiloEsperaConTok.start();
        HiloesperarMensajeSP.start();
        Servidor.setIP(Cliente_Principal.getIP());
        jPanel_inicio.setVisible(false);
        jPanelMostrarCartas.setVisible(true);
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButton_tokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tokenActionPerformed
        String elegido = grupoRB.getSelection().getActionCommand();
        Carta c = new Carta();
        System.out.println("Boton elegido " + elegido);
        switch (elegido) {
            case "seleccion1":
                c = mazoRecibido.getCartas().get(0);
                mazoCliente.addCartasMazo(c);
                break;
            case "seleccion2":
                c = mazoRecibido.getCartas().get(1);
                mazoCliente.addCartasMazo(c);
                break;
            case "seleccion3":
                c = mazoRecibido.getCartas().get(2);
                mazoCliente.addCartasMazo(c);
                break;
            default:
                System.out.println("Esa opcion no existe prro >:v");
                break;
        }

        addValoresTabla(c);
        jPanel3Cartas.setVisible(false);
        jPanelMostrarCartas.setVisible(true);
        grupoRB.clearSelection();
        BDCarta bdCJugador = new BDCarta();
        bdCJugador.guardarCartaCliente(Cliente_Principal.getJugador(), jLabel_Reloj.getText().toString(), c, Cliente_Principal.getRonda(), ServidoresNom.SERVIDOR1.getHost());
       // bdCJugador.guardarCartaCliente(Cliente_Principal.getJugador(), jLabel_Reloj.getText().toString(), c, Cliente_Principal.getRonda(), ServidoresNom.SERVIDOR2.getHost());
//        bdCJugador.guardarCartaCliente(Cliente_Principal.getJugador(), jLabel_Reloj.getText().toString(), c, Cliente_Principal.getRonda(), ServidoresNom.SERVIDOR3.getHost());
        Cliente_Principal.enviarMSJ(elegido);
        Cliente.enviarToken();
        Servidor.setToken(false);
        jButton_token.setEnabled(false);
        jButton_PedirCartas.setEnabled(false);
    }//GEN-LAST:event_jButton_tokenActionPerformed

    private void jButton_PedirCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PedirCartasActionPerformed
        Cliente_Principal.enviarMSJ("cartas");
        System.out.println("Pidiendo cartas");
        try {
            TimeUnit.SECONDS.sleep(1); // Necesario para que pueda recibir el cliente el mazo si no NullPointerException
            mazoRecibido = Cliente_Principal.getMazoCliente();
            jPanelMostrarCartas.setVisible(false);
            jPanel3Cartas.setVisible(true);
            showInformacionCartas();
            addImagenesCarta();

        } catch (InterruptedException ex) {
            Logger.getLogger(vistaClienteJuego1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_PedirCartasActionPerformed

    private void jTablePokemonSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePokemonSelectMouseClicked
        // TODO add your handling code here:
        int columna = jTablePokemonSelect.columnAtPoint(evt.getPoint());
        int fila = jTablePokemonSelect.rowAtPoint(evt.getPoint());
        JPanelCartaTabla cartaSeleccionada = new JPanelCartaTabla(mazoCliente.getCartas().get(fila));
        cartaSeleccionada.setBackground(mapcolorTipo.get(mazoCliente.getCartas().get(fila).getTipo1()));
        cartaSeleccionada.setBackground(mapcolorTipo.get(mazoCliente.getCartas().get(fila).getTipo2()));
        cartaSeleccionada.setBackground(mapcolorTipo.get(mazoCliente.getCartas().get(fila).getTipo1()));
        
        if (columna == 0 && fila >= 0) {
            JOptionPane.showConfirmDialog(null, cartaSeleccionada, mazoCliente.getCartas().get(fila).getNombre(), JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        }


    }//GEN-LAST:event_jTablePokemonSelectMouseClicked

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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionCarta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionCarta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionCarta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSeleccionCarta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new vistaClienteJuego1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PuertoPropio;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButton_PedirCartas;
    private javax.swing.JButton jButton_token;
    private javax.swing.JLabel jFondoMostrarCartas;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelFondoCartas;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JLabel jLabelImg4;
    private javax.swing.JLabel jLabelImg5;
    private javax.swing.JLabel jLabel_Reloj;
    private javax.swing.JLabel jLabelinfo;
    private javax.swing.JPanel jPanel3Cartas;
    private javax.swing.JPanel jPanelC1;
    private javax.swing.JPanel jPanelC2;
    private javax.swing.JPanel jPanelC3;
    private javax.swing.JPanel jPanelCarta1;
    private javax.swing.JPanel jPanelCarta2;
    private javax.swing.JPanel jPanelCarta3;
    private javax.swing.JPanel jPanelMostrarCartas;
    private javax.swing.JPanel jPanel_inicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePokemonSelect;
    private javax.swing.JTextField jTextField_prioridad;
    private javax.swing.JRadioButton jrbCarta1;
    private javax.swing.JRadioButton jrbCarta2;
    private javax.swing.JRadioButton jrbCarta3;
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
