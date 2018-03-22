
package Vistas;

import Reloj.reloj;
import Sockets.SockServidor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VistaServidor extends javax.swing.JFrame implements Runnable{
    public static reloj rel = new reloj();
    static Thread h1;
    Thread hserver;
    SockServidor ser;
    static String NuevoHora="0";
    static String NuevoMin="0";
    static String NuevoSeg="0";
    public VistaServidor() {
        initComponents();
        
        
        try {
            ser = new SockServidor();
            ser.iniciar();

        } catch (Exception e) {}
        
        setTitle("Servidor Hora");
        setLocationRelativeTo(null);
        setVisible(true);
        ip_puerto.setText(ser.getHOST() +":" +ser.getPUERTO());
        
        h1 = new Thread(this);
        hserver = new Thread(this);
        h1.start();
        hserver.start();
        
    }
    @Override
    public void run() 
    {
        Thread hilo = Thread.currentThread();
        while(hilo == h1 && !h1.isInterrupted())
        {
            rel.pasarTiempo();
            Hora.setText(rel.imprimeHora() );
            try 
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){}
        }
        while(hilo == hserver)
        {
            ser.aceptar();


        }
        
    }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Hora = new javax.swing.JLabel();
        ip_puerto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Hora.setFont(new java.awt.Font("Palatino Linotype", 0, 36)); // NOI18N
        Hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Hora.setText("jLabel1");
        Hora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoraMouseClicked(evt);
            }
        });

        ip_puerto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ip_puerto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ip_puerto.setText("jLabel2");
        ip_puerto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ip_puertoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ip_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(ip_puerto)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ip_puertoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ip_puertoMouseClicked
        
    }//GEN-LAST:event_ip_puertoMouseClicked

    private void HoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoraMouseClicked
        h1.interrupt();
        h1 = new Thread(this);
        VistaCambio nuevo = new VistaCambio();
        nuevo.setVisible(true);
    }//GEN-LAST:event_HoraMouseClicked


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel ip_puerto;
    // End of variables declaration//GEN-END:variables
}
