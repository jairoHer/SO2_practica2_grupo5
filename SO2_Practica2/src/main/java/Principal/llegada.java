/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.front.jLabel4;
import static Principal.front.jLabel5;
import static Principal.front.jLabel6;
import static Principal.front.jLabel7;
import static Principal.front.jLabel8;
import static Principal.front.jLabel9;
import static Principal.front.jLabel10;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Estuardo
 */
public class llegada extends Thread{
    private Estanteria est;
    public llegada(Estanteria est){
        this.est=est;
    }
    @Override
    public void run(){
        try {
            //4-10
            camino();
        } catch (InterruptedException ex) {
            Logger.getLogger(llegada.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void camino()throws InterruptedException{
        int tiempo=100;
        jLabel4.setText("|");
        Thread.sleep(tiempo);
        jLabel4.setText("");
        jLabel5.setText("|");
        Thread.sleep(tiempo);
        jLabel5.setText("");
        jLabel6.setText("|");
        Thread.sleep(tiempo);
        jLabel6.setText("");
        jLabel7.setText("|");
        Thread.sleep(tiempo);
        jLabel7.setText("");
        jLabel8.setText("|");
        Thread.sleep(tiempo);
        jLabel8.setText("");
        jLabel9.setText("|");
        Thread.sleep(tiempo);
        jLabel9.setText("");
        jLabel10.setText("|");
        Thread.sleep(tiempo);
        jLabel10.setText("");
        this.est.agregarllegada(1);
    }
}
