package PServerJuego;

import PClienteJuego.Recuperacion;
import static PServerJuego.vistaServerJuego.Servidor_Principal;
import static PServerJuego.vistaServerJuego.rel;
import Reloj.reloj;
import com.dist.DTO.BDCarta;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import com.dist.vistas.coordinador.VistaCordinador;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class vistaServerJuego1 extends javax.swing.JFrame implements Runnable {

    static Carta c1, c2, c3;
    static Mazo m1;
    static int numCartas; // Ronda
    Map<String, Color> mapcolorTipo;
    static reloj rel = new reloj();
    BDCarta bdC;
    static Recuperacion rec;
    Thread Hilo_ServidorAcceptar;
    Thread Hilo_ServidorEsperarMensajes;
    Thread HiloLamport;
    boolean estado;
    static boolean ModoServidorRespaldo;
    static ServerJuego Servidor_Principal;

    public vistaServerJuego1() {
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        bdC = new BDCarta();
        bdC.borrarTodoTablas();
        mapcolorTipo = new HashMap<String, Color>();
        int numCartas = 0;
        Servidor_Principal = new ServerJuego(3000);
        Servidor_Principal.iniciar();
        Hilo_ServidorAcceptar = new Thread(this);
        Hilo_ServidorEsperarMensajes = new Thread(this);
        HiloLamport = new Thread(this);
        Hilo_ServidorAcceptar.start();
        HiloLamport.start();        
        ModoServidorRespaldo = false;
        addValoresMapColor();
        get3Cartas();
    }

    public vistaServerJuego1(int numeroJugador, boolean tokenAnterior) {
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        mapcolorTipo = new HashMap<String, Color>();
        int numCartas = 0;
        Servidor_Principal = new ServerJuego(3000);
        Servidor_Principal.iniciar();
        Hilo_ServidorAcceptar = new Thread(this);
        Hilo_ServidorEsperarMensajes = new Thread(this);
        HiloLamport = new Thread(this);
        Hilo_ServidorAcceptar.start();
        HiloLamport.start();
        ModoServidorRespaldo = true;
        addValoresMapColor();
        rec = new Recuperacion();
        rec.iniciar(tokenAnterior, numeroJugador);
        m1 = new Mazo();
        m1 = rec.getMazoRecuperado();
        //get3Cartas();
    }

    @Override
    public void run() {

        Thread hilo = Thread.currentThread();
        if (estado == true) {
            System.out.println("Tengo el token");
        }
        while (hilo == Hilo_ServidorAcceptar && !hilo.isInterrupted()) {
            try {
                Servidor_Principal.acceptar();
                Thread.sleep(500); //el sleep esta ya que en el momento de la reconexion todos los clientes se conectan de golpe y causan problemas al momento de responderles a donde conectarse
            } catch (InterruptedException e) {
                System.out.println(e);
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

        jButton_Iniciar = new javax.swing.JButton();
        jbtnTomarCartas = new javax.swing.JButton();
        jbtnSelecCartas = new javax.swing.JButton();
        jbtnReporte = new javax.swing.JButton();
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
        jLabelFondoCartas = new javax.swing.JLabel();
        jLabel_Reloj = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego de Cartas (Servidor)");
        setBackground(new java.awt.Color(204, 255, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(860, 690));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_Iniciar.setText("Iniciar Juego");
        jButton_Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IniciarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 600, 121, 68));

        jbtnTomarCartas.setText("Repartir Mazo");
        jbtnTomarCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTomarCartasActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnTomarCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, 133, -1));

        jbtnSelecCartas.setText("Seleccionar 3 cartas");
        jbtnSelecCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelecCartasActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSelecCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 580, 156, 68));

        jbtnReporte.setText("Reporte");
        jbtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnReporteActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 131, 68));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanelCarta1Layout = new javax.swing.GroupLayout(jPanelCarta1);
        jPanelCarta1.setLayout(jPanelCarta1Layout);
        jPanelCarta1Layout.setHorizontalGroup(
            jPanelCarta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelC1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCarta1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
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
                            .addComponent(jtfTipo2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(jPanelCarta1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanelCarta2Layout = new javax.swing.GroupLayout(jPanelCarta2);
        jPanelCarta2.setLayout(jPanelCarta2Layout);
        jPanelCarta2Layout.setHorizontalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)))
                .addGap(67, 67, 67))
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
                            .addComponent(jtfTipo2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCarta2Layout.setVerticalGroup(
            jPanelCarta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
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
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 6, -1, -1));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanelCarta3Layout = new javax.swing.GroupLayout(jPanelCarta3);
        jPanelCarta3.setLayout(jPanelCarta3Layout);
        jPanelCarta3Layout.setHorizontalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarta3Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jPanelC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addGroup(jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
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
                                    .addComponent(jtfTipo2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelCarta3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCarta3Layout.setVerticalGroup(
            jPanelCarta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarta3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jPanel3Cartas.add(jPanelCarta3, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 6, -1, -1));

        jLabelFondoCartas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jLabelFondoCartas.setText("jLabel1");
        jLabelFondoCartas.setPreferredSize(new java.awt.Dimension(843, 562));
        jPanel3Cartas.add(jLabelFondoCartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -4, 843, 562));

        getContentPane().add(jPanel3Cartas, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 22, -1, -1));

        jLabel_Reloj.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_Reloj.setText("Reloj");
        getContentPane().add(jLabel_Reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, -1, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dist/imagenes/fondos/fondo1.png"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        jLabelFondo.setMaximumSize(new java.awt.Dimension(860, 690));
        jLabelFondo.setMinimumSize(new java.awt.Dimension(860, 690));
        jLabelFondo.setName(""); // NOI18N
        jLabelFondo.setPreferredSize(new java.awt.Dimension(860, 690));
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 860, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IniciarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton_IniciarActionPerformed

    private void jbtnTomarCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTomarCartasActionPerformed

        //Agregar funcionalidad para que al presionar el boton se envien las 3 cartas que salieron
//        atomics.addAndGet(1);
//        enviarCartas();

    }//GEN-LAST:event_jbtnTomarCartasActionPerformed

    private void jbtnSelecCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelecCartasActionPerformed
        if (numCartas <= 4) {
            get3Cartas();
        } else {
            try {
                jbtnSelecCartas.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Solo se pueden tener 5 cartas en cada mazo");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbtnSelecCartasActionPerformed

    public void get3Cartas() {
        bdC = new BDCarta();
        m1 = new Mazo();
        c1 = new Carta();
        c2 = new Carta();
        c3 = new Carta();

        c1.getCartaAleatoria();
        c2.getCartaAleatoria();
        c3.getCartaAleatoria();

        c1.addImagenCarta();
        c2.addImagenCarta();
        c3.addImagenCarta();

        addImagenesCarta();//añade las imagenes de los pokemones a la pantalla
        addInformacionPokemon();//Imprime en pantalla estadisticas de cada carta
        crearMazos();//añade las cartas al mazo para darlas despues a los jugadores

        bdC.guardarMazoServidor(m1);
        Servidor_Principal.setMazoServidor(m1);
    }
    
    public void recuperarEstadoAnterior()
    {
        // colocar numero de ronda
        // recuperar cartas seleccionadas
        // ingresar cartas nuevas
        // enviar el token si lo tenia
    }

    public void addInformacionPokemon() {

        //CARTA 1
        jtfNombre1.setText(c1.getNombre());
        jtfTipo1_1.setText(c1.getTipo1());
        jtfTipo2_1.setText(c1.getTipo2());
        jtfHP1.setText("" + c1.getHp());
        jtfAtaque1.setText("" + c1.getAtaque());
        jtfDefensa1.setText("" + c1.getDefensa());
        //Cambiando colores
        jtfTipo1_1.setBackground(mapcolorTipo.get(c1.getTipo1()));
        jtfTipo2_1.setBackground(mapcolorTipo.get(c1.getTipo2()));
        jPanelCarta1.setBackground(mapcolorTipo.get(c1.getTipo1()));

        //CARTA 2
        jtfNombre2.setText(c2.getNombre());
        jtfTipo1_2.setText(c2.getTipo1());
        jtfTipo2_2.setText(c2.getTipo2());
        jtfHP2.setText("" + c2.getHp());
        jtfAtaque2.setText("" + c2.getAtaque());
        jtfDefensa2.setText("" + c2.getDefensa());
        jtfTipo1_2.setBackground(mapcolorTipo.get(c2.getTipo1()));
        jtfTipo2_2.setBackground(mapcolorTipo.get(c2.getTipo2()));
        jPanelCarta2.setBackground(mapcolorTipo.get(c2.getTipo1()));

        //CARTA 3
        jtfNombre3.setText(c3.getNombre());
        jtfTipo1_3.setText(c3.getTipo1());
        jtfTipo2_3.setText(c3.getTipo2());
        jtfHP3.setText("" + c3.getHp());
        jtfAtaque3.setText("" + c3.getAtaque());
        jtfDefensa3.setText("" + c3.getDefensa());
        jtfTipo1_3.setBackground(mapcolorTipo.get(c3.getTipo1()));
        jtfTipo2_3.setBackground(mapcolorTipo.get(c3.getTipo2()));
        jPanelCarta3.setBackground(mapcolorTipo.get(c3.getTipo1()));

    }

    public void addImagenesCarta() {

        jPanelC1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c1.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg3.setIcon(c1.getImagenPokemon());

        jPanelC2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c2.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg4.setIcon(c2.getImagenPokemon());

        jPanelC3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, c3.getNombre(), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jLabelImg5.setIcon(c3.getImagenPokemon());
    }

    public void addValoresMapColor() {
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

    public void crearMazos() {
        m1.addCartasMazo(c1);
        m1.addCartasMazo(c2);
        m1.addCartasMazo(c3);
        numCartas++;
    }


    private void jbtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnReporteActionPerformed
                ReporteCordinador rc = new ReporteCordinador();
                rc.setVisible(true);
    }//GEN-LAST:event_jbtnReporteActionPerformed
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
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new vistaServerJuego1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iniciar;
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
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelFondoCartas;
    private javax.swing.JLabel jLabelImg3;
    private javax.swing.JLabel jLabelImg4;
    private javax.swing.JLabel jLabelImg5;
    private javax.swing.JLabel jLabel_Reloj;
    private javax.swing.JPanel jPanel3Cartas;
    private javax.swing.JPanel jPanelC1;
    private javax.swing.JPanel jPanelC2;
    private javax.swing.JPanel jPanelC3;
    private javax.swing.JPanel jPanelCarta1;
    private javax.swing.JPanel jPanelCarta2;
    private javax.swing.JPanel jPanelCarta3;
    private javax.swing.JButton jbtnReporte;
    public static javax.swing.JButton jbtnSelecCartas;
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
