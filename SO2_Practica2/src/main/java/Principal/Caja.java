/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import static Principal.front.jTextField1;
import static Principal.front.jTextField2;
import static Principal.front.jTextField3;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Estuardo
 */
public class Caja extends Thread{
    private Principal.Estanteria est;
    public Caja(Principal.Estanteria est){
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
            if(this.est.getllegada()>0 && this.est.getcajas()<20){
                this.est.agregarcajas(1);
                this.est.agregarllegada(-1);
            }
            if(this.est.getsalida()>0 && this.est.getcajas()>0){
                this.est.agregarcajas(-1);
                this.est.agregarsalida(-1);
            }
            
            Thread.sleep(100);
        }
    }
    
}
