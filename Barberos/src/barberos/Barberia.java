/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barberos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class Barberia implements Runnable{

    int sillas_espera;
    int sillas_ocupadas = 0;
    boolean pelo_cortado = false;
    Barbero barbero1,barbero2;
    int clientes = 0;
    Ventana frame;
    
    public Barberia(int cantidad_sillas,Ventana frame) throws InterruptedException {
        this.sillas_espera = cantidad_sillas;
        this.frame = frame;
        this.barbero1 = new Barbero(1,frame);
        this.frame.EstadoBarbero(1,1);
        this.barbero2 = new Barbero(2,frame);
        this.frame.EstadoBarbero(1,2);
    }
    
    @Override
    public void run() {
        this.clientes++;
        int cliente = this.clientes;
        if (sillas_ocupadas < sillas_espera) {
            System.out.println(">_Cliente No: "+cliente+" entro al establecimiento");
            sillas_ocupadas++;
            this.frame.NumFila(sillas_ocupadas);
            System.out.println(">_Cliente No: "+cliente+" se sienta en la silla de espera");
            
            while(this.barbero1.silla_barbero && this.barbero2.silla_barbero) {
                
            }
            sillas_ocupadas--;
            this.frame.NumFila(sillas_ocupadas);
            
            pelo_cortado = false;
            if(!this.barbero1.silla_barbero){
                this.frame.EstadoSilla(1, 1, cliente);
                this.barbero1.silla_barbero = true;
                if(this.barbero1.barbero_durmiendo) {
                    System.out.println(">_Cliente No: "+cliente+" despierta al barbero");
                    try {
                        this.barbero1.despertar_barbero();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(">_Cliente No:"+cliente+" listo para que le corte el pelo el barbero 1");
                Thread corte_barbero1 = new Thread(barbero1);
                corte_barbero1.start();
                try {
                    corte_barbero1.join();
                    this.frame.EstadoBarbero(1,1);
                    this.frame.EstadoSilla(2, 1, cliente);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(!this.barbero2.silla_barbero){
                this.frame.EstadoSilla(1, 2, cliente);
                this.barbero2.silla_barbero = true;
                if(this.barbero2.barbero_durmiendo) {
                    System.out.println(">_Cliente No: "+cliente+" despierta al barbero");
                    try {
                        this.barbero2.despertar_barbero();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(">_Cliente No:"+cliente+" listo para que le corte el pelo el barbero 2");
                Thread corte_barbero2 = new Thread(barbero2);
                corte_barbero2.start();
                try {
                    corte_barbero2.join();
                    this.frame.EstadoBarbero(1,2);
                    this.frame.EstadoSilla(2, 2, cliente);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(">_Cliente No: "+cliente+" se va con el pelo cortado! :D");
        } else {
            System.out.println(">_Cliente No: "+cliente+" no pudo entrar y se fue a otro establecimiento :(");
        }
    }   

}
