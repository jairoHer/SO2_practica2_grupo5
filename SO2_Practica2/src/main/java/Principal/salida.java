/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.front.jLabel11;
import static Principal.front.jLabel12;
import static Principal.front.jLabel13;
import static Principal.front.jLabel14;
import static Principal.front.jLabel15;
import static Principal.front.jLabel16;
import static Principal.front.jLabel17;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Estuardo
 */
public class salida extends Thread{
private Estanteria est;
    public salida(Estanteria est){
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
        jLabel11.setText("|");
        Thread.sleep(tiempo);
        jLabel11.setText("");
        jLabel12.setText("|");
        Thread.sleep(tiempo);
        jLabel12.setText("");
        jLabel13.setText("|");
        Thread.sleep(tiempo);
        jLabel13.setText("");
        jLabel14.setText("|");
        Thread.sleep(tiempo);
        jLabel14.setText("");
        jLabel15.setText("|");
        Thread.sleep(tiempo);
        jLabel15.setText("");
        jLabel16.setText("|");
        Thread.sleep(tiempo);
        jLabel16.setText("");
        jLabel17.setText("|");
        Thread.sleep(tiempo);
        jLabel17.setText("");
        this.est.agregarsalida(1);
    }
}
