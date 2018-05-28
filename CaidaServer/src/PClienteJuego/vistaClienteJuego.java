package PClienteJuego;
import token.tokenCliente;
import token.tokenServer;


public class vistaClienteJuego extends javax.swing.JFrame implements Runnable {
    tokenServer Servidor; // tokenserver y cliente se encargan solamente de los procesos relacionados al token
    tokenCliente Cliente;
    ClienteJuego Cliente_Principal;
    Thread HiloEsperaToken; //Hilo del servidor para esperar el token
    Thread HiloEsperaConTok; //Hilo para la espera de la conexion, necesario para evitar el bloqueo del programa   
    Thread HiloesperarMensajeSP; //Hilo de espera de mensajes del servidor principal del juego 
    Boolean token = false;
    int prioridad;

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
                
    }
    
    @Override
    public void run() 
    {
        String buffer;
        int estado_mensajes;
        boolean bandera = false;
        Thread hilo = Thread.currentThread();
        while(hilo == HiloEsperaConTok) // Este hilo se puede parar por completo tan pronto acepta ya que solo requiere la primer conexion
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
        while(hilo == HiloEsperaToken) // este hilo es para recibir los mensajes que se mandan entre los jugadores
        {
            buffer = Servidor.EsperarMensaje();           
            jButton_token.setEnabled(Servidor.isToken());
            if(!buffer.equals("Token"))
                Cliente.accion(buffer);           
        }
        while(hilo == HiloesperarMensajeSP)
        {
            estado_mensajes = Cliente_Principal.IterprestarMensaje();
            if( estado_mensajes == 1 )
            {
                Cliente = new tokenCliente(Cliente_Principal.getIP_siguiente(), Cliente_Principal.getPuerto_siguiente());                     
                Cliente.setPrioridad(prioridad);
                Servidor.setPrioridad(prioridad);
                if(Cliente_Principal.getJugador() == 1)
                    token = true;
                jButton_token.setEnabled(token);
            }
            else if(estado_mensajes == 0)
            {
                System.out.println("El servidor murio");
                Cliente.enviarMSJ(jTextField_prioridad.getText());
                HiloesperarMensajeSP.stop(); // con interrupt se cicla y no muere el hilo
                HiloesperarMensajeSP.interrupt();
            }
        }        
    }
    
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PuertoPropio = new javax.swing.JTextField();
        jLabelinfo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jButton_token = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_prioridad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP");

        jLabel2.setText("Puerto");

        jLabel3.setText("Info jugador siguiente");

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

        jLabel6.setText("Prioridad");

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
                                    .addComponent(jTextField_prioridad))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(61, 61, 61)))))
                .addGap(33, 33, 33))
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
                    .addComponent(PuertoPropio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField_prioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jButtonIniciar)
                .addGap(13, 13, 13)
                .addComponent(jButton_token)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
        int t;
        Servidor = new tokenServer(Integer.valueOf(PuertoPropio.getText())); //servidor para token y caida del server
        Cliente_Principal = new ClienteJuego("localhost", 3000); //Puerto para servidor y cliente principal 3000
        Servidor.iniciar();
        Cliente_Principal.iniciar(PuertoPropio.getText());
        prioridad = Integer.valueOf(jTextField_prioridad.getText());        
        HiloEsperaConTok.start();
        HiloesperarMensajeSP.start();
        Servidor.setIP(Cliente_Principal.getIP());
        jButtonIniciar.setEnabled(false);
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jButton_tokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tokenActionPerformed
        Cliente.enviarToken();
        jButton_token.setEnabled(false);
    }//GEN-LAST:event_jButton_tokenActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vistaClienteJuego().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PuertoPropio;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JButton jButton_token;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelinfo;
    private javax.swing.JTextField jTextField_prioridad;
    // End of variables declaration//GEN-END:variables
}
