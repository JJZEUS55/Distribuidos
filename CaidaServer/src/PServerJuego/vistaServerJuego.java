
package PServerJuego;

/**
 *
 * @author Alan
 */
public class vistaServerJuego extends javax.swing.JFrame implements Runnable
{
    Thread ServidorAcceptar;
    ServerJuego Servidor_Principal;
    
    public vistaServerJuego() 
    {
        initComponents();
        Servidor_Principal = new ServerJuego(3000);
        Servidor_Principal.iniciar();
        ServidorAcceptar = new Thread(this);
        ServidorAcceptar.start();
    }

    @Override
    public void run() 
    {
        Thread hilo = Thread.currentThread();
        while(hilo == ServidorAcceptar)
        {
            Servidor_Principal.acceptar();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Iniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_Iniciar.setText("Iniciar Juego");
        jButton_Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jButton_Iniciar)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jButton_Iniciar)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IniciarActionPerformed
        // TODO add your handling code here:
        Servidor_Principal.iniciarJuego();
    }//GEN-LAST:event_jButton_IniciarActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vistaServerJuego().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iniciar;
    // End of variables declaration//GEN-END:variables
}
