/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.front.jTextField1;
import static Principal.front.jTextField2;
import static Principal.front.jTextField3;
import static Principal.front.jRadioButton1;
import static Principal.front.jRadioButton2;
import static Principal.front.jRadioButton3;
import static Principal.front.jRadioButton4;
import static Principal.front.jRadioButton5;
import static Principal.front.jRadioButton6;
import static Principal.front.jRadioButton7;
import static Principal.front.jRadioButton8;
import static Principal.front.jRadioButton9;
import static Principal.front.jRadioButton10;
import static Principal.front.jRadioButton11;
import static Principal.front.jRadioButton12;
import static Principal.front.jRadioButton13;
import static Principal.front.jRadioButton14;
import static Principal.front.jRadioButton15;
import static Principal.front.jRadioButton16;
import static Principal.front.jRadioButton17;
import static Principal.front.jRadioButton18;
import static Principal.front.jRadioButton19;
import static Principal.front.jRadioButton20;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Estuardo
 */
public class Pintar extends Thread{
    private Principal.Estanteria est;
    public Pintar(Principal.Estanteria est){
        this.est=est;
    }
    
    @Override
    public void run(){
        try {
            cambiando();
        } catch (InterruptedException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiando()throws InterruptedException{
        while(true){
            jTextField1.setText(String.valueOf(this.est.getllegada()));
            jTextField2.setText(String.valueOf(this.est.getsalida()));
            jTextField3.setText(String.valueOf(this.est.getcajas()));
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
            jRadioButton6.setSelected(false);
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            jRadioButton9.setSelected(false);
            jRadioButton10.setSelected(false);
            jRadioButton11.setSelected(false);
            jRadioButton12.setSelected(false);
            jRadioButton13.setSelected(false);
            jRadioButton14.setSelected(false);
            jRadioButton15.setSelected(false);
            jRadioButton16.setSelected(false);
            jRadioButton17.setSelected(false);
            jRadioButton18.setSelected(false);
            jRadioButton19.setSelected(false);
            jRadioButton20.setSelected(false);
            switch(this.est.getcajas()){
                case 20:
                   jRadioButton20.setSelected(true);
                case 19:
                   jRadioButton19.setSelected(true);
                case 18:
                   jRadioButton18.setSelected(true);
                case 17:
                   jRadioButton17.setSelected(true);
                case 16:
                   jRadioButton16.setSelected(true);
                case 15:
                   jRadioButton15.setSelected(true);
                case 14:
                   jRadioButton14.setSelected(true);
                case 13:
                   jRadioButton13.setSelected(true);
                case 12:
                   jRadioButton12.setSelected(true);
                case 11:
                   jRadioButton11.setSelected(true);
                case 10:
                   jRadioButton10.setSelected(true);
                case 9:
                   jRadioButton9.setSelected(true);
                case 8:
                   jRadioButton8.setSelected(true);
                case 7:
                   jRadioButton7.setSelected(true);
                case 6:
                   jRadioButton6.setSelected(true);
                case 5:
                   jRadioButton5.setSelected(true);
                case 4:
                   jRadioButton4.setSelected(true);
                case 3:
                   jRadioButton3.setSelected(true);
                case 2:
                   jRadioButton2.setSelected(true);
                case 1:
                   jRadioButton1.setSelected(true);
            }
            Thread.sleep(100);
        }
    }
}
