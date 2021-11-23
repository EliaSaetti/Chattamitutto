/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattamituttop;

/**
 *
 * @author saetti_elia
 */
public class CMessaggio {

    String comando;
    String dato;

    public CMessaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
    }

    public static CMessaggio FromCSV(String csv) {
        int index = csv.indexOf(";");
        String com = csv.substring(0, index);
        String dat = csv.substring(index + 1, csv.length());
        return new CMessaggio(com, dat);
    }
}
