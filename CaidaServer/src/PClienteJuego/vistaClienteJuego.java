package PClienteJuego;
import PServerJuego.vistaServerJuego;
import Reloj.reloj;
import token.tokenCliente;
import token.tokenServer;


public class vistaClienteJuego extends javax.swing.JFrame implements Runnable {
    tokenServer Servidor; // tokenserver y cliente se encargan solamente de los procesos relacionados al token
    tokenCliente Cliente;
    ClienteJuego Cliente_Principal;
    Thread HiloEsperaToken; //Hilo del servidor para esperar el token
    Thread HiloEsperaConTok; //Hilo para la espera de la conexion, necesario para evitar el bloqueo del programa   
    Thread HiloesperarMensajeSP; //Hilo de espera de mensajes del servidor principal del juego
    Thread HiloLamport;
    Boolean funcionamiento = false;
    int prioridad;
    static reloj rel = new reloj();
    public vistaClienteJuego() 
    {
        initComponents();
        setTitle("Jugador");
        setLocationRelativeTo(null);
        setVisible(true);
        jButton_token.setEnabled(false);
        HiloEsperaConTok = new Thread(this);
        HiloesperarMensajeSP = new Thread(this);
        HiloEsperaToken = new Thread(this);
        HiloLamport = new Thread(this);
        HiloLamport.start();
                
    }
    
    @Override
    public void run() 
    {
        
        String buffer;
        int estado_mensajes;
        boolean bandera = false;
        
        Thread hilo = Thread.currentThread();
        while(hilo == HiloEsperaConTok) 
        {
            Servidor.acceptar();
            System.out.println("...");         
            if(bandera == false)
            {
                System.out.println("Iniciando Hilo");
                HiloEsperaToken.start();      
                bandera = true;
            }  
            else
            {
                HiloEsperaToken.interrupt();
                HiloEsperaToken = new Thread(this);
                HiloEsperaToken.start();
                System.out.println("Reiniciando hilo");
            }
        }
        while(hilo == HiloEsperaToken && !hilo.isInterrupted()) // este hilo es para recibir los mensajes que se mandan entre los jugadores
        {
            buffer = Servidor.EsperarMensaje(); 
            jButton_token.setEnabled(Servidor.isToken());
            jButton_PedirCartas.setEnabled(Servidor.isToken());
            if(!buffer.equals("Token"))
                Cliente.accion(buffer);   
            if(Servidor.isElegido())// se checan banderas dentro de las clases token para iniciar el nuevo servidor en uno de los jugadores 
            {  
                System.out.println("Iniciando nuevo servidor......");
                vistaServerJuego n = new vistaServerJuego(Servidor.isToken());
                n.setVisible(true);
                this.setVisible(false);
                HiloEsperaToken.interrupt();
            }
            else if (Cliente.isCancelarReenvio())// o conectarse al nuevo servidor segun sea el caso
            {               
                System.out.println("Conectando al nuevo servidor("+Servidor.getIPNuevoServer()+")...");
                Cliente_Principal = new ClienteJuego(Servidor.getIPNuevoServer(), 3000);
                Cliente_Principal.iniciar(PuertoPropio.getText());  
                HiloesperarMensajeSP = new Thread(this);
                HiloesperarMensajeSP.start();
            }
        }
        while(hilo == HiloesperarMensajeSP && !hilo.isInterrupted())
        {
            estado_mensajes = Cliente_Principal.IterprestarMensaje();
            if( estado_mensajes == 1 )
            {
                Cliente = new tokenCliente(Cliente_Principal.getIP_siguiente(), Cliente_Principal.getPuerto_siguiente());                     
                Cliente.setPrioridad(prioridad);
                Servidor.setPrioridad(prioridad);
                if(Cliente_Principal.getJugador() == 1 && funcionamiento == false){                            
                    Servidor.setToken(true);
                }
                jButton_token.setEnabled(Servidor.isToken());
                jButton_PedirCartas.setEnabled(Servidor.isToken());
                funcionamiento = true;
            }
            else if(estado_mensajes == 0)
            {
                System.out.println("El servidor murio");
                Cliente.enviarMSJ(jTextField_prioridad.getText());
                HiloesperarMensajeSP.interrupt();
            }
        }
        while (hilo == HiloLamport)
        {
            rel.pasarTiempo();
            jLabel_Reloj.setText(rel.imprimeHora());
            try {Thread.sleep(1000);}
            catch (InterruptedException e){}
        }
    }
    
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_inicio = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_prioridad = new javax.swing.JTextField();
        PuertoPropio = new javax.swing.JTextField();
        jLabelinfo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton_token = new javax.swing.JButton();
        jButton_PedirCartas = new javax.swing.JButton();
        jLabel_Reloj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Prioridad");

        PuertoPropio.setText("300");

        jLabelinfo.setText("Info propia");

        jLabel4.setText("Puerto");

        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jLabel5.setText("Jugador");

        javax.swing.GroupLayout jPanel_inicioLayout = new javax.swing.GroupLayout(jPanel_inicio);
        jPanel_inicio.setLayout(jPanel_inicioLayout);
        jPanel_inicioLayout.setHorizontalGroup(
            jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_inicioLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabelinfo))
                    .addGroup(jPanel_inicioLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButtonIniciar))
                    .addGroup(jPanel_inicioLayout.createSequentialGroup()
                        .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PuertoPropio)
                            .addComponent(jTextField_prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_inicioLayout.setVerticalGroup(
            jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelinfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PuertoPropio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jButtonIniciar)
                .addGap(75, 75, 75))
        );

        jButton_token.setText("Seleccionar carta");
        jButton_token.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tokenActionPerformed(evt);
            }
        });

        jButton_PedirCartas.setText("Pedir Cartas");
        jButton_PedirCartas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PedirCartasActionPerformed(evt);
            }
        });

        jLabel_Reloj.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel_Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Reloj.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Reloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_token))
                    .addComponent(jButton_PedirCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton_PedirCartas)
                .addGap(28, 28, 28)
                .addComponent(jButton_token)
                .addGap(28, 28, 28)
                .addComponent(jLabel_Reloj, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        Servidor = new tokenServer(Integer.valueOf(PuertoPropio.getText())); //servidor para token y caida del server
        Cliente_Principal = new ClienteJuego("localhost", 3000); //Puerto para servidor y cliente principal 3000
        Servidor.iniciar();
        Cliente_Principal.iniciar(PuertoPropio.getText());
        prioridad = Integer.valueOf(jTextField_prioridad.getText());        
        HiloEsperaConTok.start();
        HiloesperarMensajeSP.start();
        Servidor.setIP(Cliente_Principal.getIP());
        jPanel_inicio.setVisible(false);
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButton_tokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tokenActionPerformed
        Cliente.enviarToken();
        Servidor.setToken(false);
        jButton_token.setEnabled(false);
        jButton_PedirCartas.setEnabled(false);
    }//GEN-LAST:event_jButton_tokenActionPerformed

    private void jButton_PedirCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PedirCartasActionPerformed
        Cliente_Principal.enviarMSJ("cartas");
        System.out.println("Pidiendo cartas");
    }//GEN-LAST:event_jButton_PedirCartasActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vistaClienteJuego().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PuertoPropio;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButton_PedirCartas;
    private javax.swing.JButton jButton_token;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_Reloj;
    private javax.swing.JLabel jLabelinfo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_inicio;
    private javax.swing.JTextField jTextField_prioridad;
    // End of variables declaration//GEN-END:variables
}
