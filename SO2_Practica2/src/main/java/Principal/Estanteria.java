/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Principal.front.jTextField1;
import static Principal.front.jTextField2;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Estuardo
 */
public class Estanteria extends Thread{
    private int listacajas;
    private int dejar;
    private int recoger;
    private Caja caja;
    private Pintar pintar;
    private ReentrantLock candado = new ReentrantLock();
    public Estanteria(){
        this.listacajas= 0;
        this.dejar=0;
        this.recoger=0;
        this.caja=new Caja(this);
        this.caja.start();
        this.pintar=new Pintar(this);
        this.pintar.start();
    }
    @Override
    public void run(){
        try {
            iniciar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Estanteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getllegada(){
        return this.dejar;
    }
    public int getsalida(){
        return this.recoger;
    }
    public int getcajas(){
        return this.listacajas;
    }
    public void agregarcajas(int numero){
        this.candado.lock();
        this.listacajas+=numero;
        this.candado.unlock();
    }
    
    public void agregarllegada(int numero){
        this.candado.lock();
        this.dejar+=numero;
        this.candado.unlock();
    }
    
    public void agregarsalida(int numero){
        this.candado.lock();
        this.recoger+=numero;
        this.candado.unlock();
    }
    
    public void iniciar()throws InterruptedException{
        while(true){
            int valordado = (int) Math.floor(Math.random()*10);
            if(valordado>4){
                // Llega a dejar una caja
                System.out.println("Se llego a dejar una caja");
                llegada lleg= new llegada(this);
                lleg.start();
                Thread.sleep(1000);
            }else{
                // Llega a recoger una caja
                System.out.println("Se llego a recoger una caja");                
                salida sal= new salida(this);
                sal.start();
                Thread.sleep(1000);
                
            }
        }
        
        
    }
}
