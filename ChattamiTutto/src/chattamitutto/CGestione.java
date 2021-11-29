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

    InetAddress lastAddress;
    int lastPort;
    int stato;
    String nickName;
    String ip;
    int statoY;

    public CGestione() throws SocketException {
        server = new DatagramSocket(2003);
        lastAddress = null;
        lastPort = 2003;
        stato = 0;
        nickName = "";
        ip = "";
        statoY = 0;
    }

    public void setPorta(int porta) {
        lastPort = porta;
    }

    public int getPorta() {
        return lastPort;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int s) {
        stato = s;
    }

    public void setNickName(String nick) {
        nickName = nick;
    }

    public String getNickName() {
        return nickName;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getIP() {
        return ip;
    }

    public void setStatoY(int y) {
        statoY = y;
    }

    public int getStatoY() {
        return statoY;
    }

    synchronized public void Invia(String s) throws IOException {
        /*if (lastAddress == null || lastPort == 0) {
            return;
        }*/

        byte[] buffer = s.getBytes();
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        p.setAddress(lastAddress);
        p.setPort(lastPort);
        server.send(p);
    }

    public String Ricevi() throws IOException {
        byte[] buffer = new byte[1500];
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        server.receive(p);

        String ip = p.getAddress().toString().substring(1);
        setIP(ip);
        lastAddress = p.getAddress();
        lastPort = p.getPort();
        String messaggio = new String(p.getData(), 0, p.getLength());
        return messaggio;
    }

    public void ShowMessage(JTextArea j1, String mess1) {
        String mess2;
        mess2 = mess1.substring(2) + "\n";
        j1.append(mess2);
    }

}
