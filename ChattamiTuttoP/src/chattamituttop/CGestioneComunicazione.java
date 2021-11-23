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

/**
 *
 * @author saetti_elia
 */
public class CGestioneComunicazione {
    
    DatagramSocket server;
    int stato=0;

    
    InetAddress lastAddress=null;
    int lastPort=0;
    
    public CGestioneComunicazione() throws SocketException {
        server= new DatagramSocket(12345);
    }
    
    public synchronized void setStato(){
    }
    
    public synchronized int getStato(){
        return stato;
    }
    
    
    public CMessaggio Ricevi() throws IOException
    {
        byte[] buffer= new byte[1500];
        DatagramPacket p = new DatagramPacket(buffer,buffer.length);
        server.receive(p);
        
        lastAddress = p.getAddress();
        lastPort = p.getPort();
        String messaggio= new String(p.getData(),0,p.getLength());
        return CMessaggio.FromCSV(messaggio);
    }
    
    public void InstauraComunicazione() throws IOException
    {
        
    }
    
    
    public void Invia(String s) throws IOException
    {
        if(lastAddress== null || lastPort==0)
            return;
        
        byte[] buffer = s.getBytes();
        DatagramPacket p = new DatagramPacket(buffer,buffer.length);
        p.setAddress(lastAddress);
        p.setPort(lastPort);
        server.send(p);
    }
    
    
}
