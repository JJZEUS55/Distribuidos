
package PServerJuego;

import Reloj.reloj;

/**
 *
 * @author Alan
 */
public class vistaServerJuego extends javax.swing.JFrame implements Runnable
{
    Thread Hilo_ServidorAcceptar;
    Thread HiloLamport;
    boolean estado;
    static ServerJuego Servidor_Principal;
    static reloj rel = new reloj();
    
    public vistaServerJuego() 
    {
        initComponents();
        Servidor_Principal = new ServerJuego(3100);
        Servidor_Principal.iniciar();
        Hilo_ServidorAcceptar = new Thread(this);
        HiloLamport = new Thread(this);
        Hilo_ServidorAcceptar.start(); 
        HiloLamport.start();
        estado = false;   

    }
    
    public vistaServerJuego(boolean tokenAnterior) 
    {
        initComponents();
        Servidor_Principal = new ServerJuego(3000);
        Servidor_Principal.iniciar();
        Hilo_ServidorAcceptar = new Thread(this);
        Hilo_ServidorAcceptar.start();
        estado = tokenAnterior;

    }

    @Override
    public void run() 
    {
        
        Thread hilo = Thread.currentThread();
        if(estado == true)
            System.out.println("Tengo el token");
        while(hilo == Hilo_ServidorAcceptar && !hilo.isInterrupted())
        {
            try {
                Servidor_Principal.acceptar();
                Thread.sleep(500); //el sleep esta ya que en el momento de la reconexion todos los clientes se conectan de golpe y causan problemas al momento de responderles a donde conectarse
            } catch (InterruptedException e) {System.out.println(e);}            
        }    
        while(hilo == HiloLamport)
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

        jLabel_Reloj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_Reloj.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel_Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Reloj.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel_Reloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel_Reloj, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addGap(24, 24, 24)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vistaServerJuego().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_Reloj;
    // End of variables declaration//GEN-END:variables
}
