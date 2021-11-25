/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattamitutto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author saetti_elia
 */
public class CGestione {

    DatagramSocket server;

    InetAddress lastAddress = null;
    int lastPort = 0;
    int stato=0;

    public CGestione() throws SocketException {
        server = new DatagramSocket(2003);
    }

    synchronized public void setPorta(int porta) {
        lastPort = porta;
    }

    synchronized public int getPorta() {
        return lastPort;
    }
    
    synchronized public int getStato(){
        return stato;
    }
    
    synchronized public void setStato(int s){
        stato = s;
    }

    public void Invia(String s) throws IOException {
        if (lastAddress == null || lastPort == 0) {
            return;
        }

        byte[] buffer = s.getBytes();
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        p.setAddress(lastAddress);
        p.setPort(lastPort);
        server.send(p);
    }

    public void ShowMessage(JTextArea j1, String mess1) {
        String mess2;
        mess2 = mess1.substring(2) + "\n";
        j1.append(mess2);
    }

}
