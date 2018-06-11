
package PServerJuego;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastS 
{
    int port = 5000;
    String group = "225.4.5.6";
    public MulticastS () {}
    public void iniciar()
    {
        try{
            MulticastSocket s = new MulticastSocket();
            byte[] buf = "Pokemon".getBytes();
            DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName(group), port);
            s.send(pack,(byte)256);
            s.close();
        } catch(IOException e){e.printStackTrace();}
        

    }
    
}
