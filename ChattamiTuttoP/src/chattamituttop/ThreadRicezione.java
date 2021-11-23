/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattamituttop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saetti_elia
 */
public class ThreadRicezione extends Thread {

    DatagramSocket server;
    CGestioneComunicazione com;

    InetAddress lastAddress = null;
    int lastPort = 0;

    public ThreadRicezione() throws SocketException {
        server = new DatagramSocket(12345);
        com = new CGestioneComunicazione();
    }

    @Override
    public void run() {
        if (com.getStato() == 1) {
            while (com.getStato() == 1) {
                try {
                    com.Ricevi();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(com.getStato() == 0){
            
        }
    }

}
