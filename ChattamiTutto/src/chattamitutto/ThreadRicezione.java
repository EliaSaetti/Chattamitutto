/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattamitutto;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author saetti_elia
 */
public class ThreadRicezione extends Thread {

    CGestione gest;
    JFrame F;

    public ThreadRicezione(JFrame F) throws SocketException {
        gest = new CGestione();
        this.F = F;
        gest.setStatoY(0);
        
    }

    @Override
    public void run() {
        while (true) {
            String mess = "";
            String nickName = "";
            String messRic;
            int statoY;
            try {
                mess = gest.Ricevi();

                if (mess.charAt(0) == 'c' && gest.getStato() == 0) {
                    nickName = mess.substring(2, mess.length());
                    int scelta = JOptionPane.showConfirmDialog(F, "Vuoi accettare la connessione con " + nickName, "Richiesta connessione", JOptionPane.YES_NO_OPTION);
                    if (scelta == 0) {
                        gest.Invia("y;" + gest.getNickName());
                    } else {
                        gest.Invia("n;");
                    }
                } else if (mess.charAt(0) == 'm' && gest.getStato() == 1) {
                    messRic = mess.substring(2, mess.length());
                } else if (mess == "n;") {
                    JOptionPane.showMessageDialog(F, "La connessione con " + nickName + " NON è stata accettata");
                } else if (mess == "y;") {
                    if (gest.getStatoY() == 0) {
                        gest.Invia("y;");
                        gest.setStatoY(1);
                    }
                    JOptionPane.showMessageDialog(F, "La connessione con " + nickName + " è stata accettata");
                } else if (mess=="e;"){
                    JOptionPane.showMessageDialog(F, "La connessione con " + nickName + " è stata chiusa");
                }
                
                gest.ShowMessage(F.jTextArea1, mess);

            } catch (IOException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
