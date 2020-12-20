/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barberos;

/**
 *
 * @author carlo
 */
public class Barbero implements Runnable {

    int num_barbero;
    boolean barbero_durmiendo = false;
    boolean silla_barbero = false;
    boolean cortando_pelo = false;
    Ventana frame;
    public Barbero(int num_barbero,Ventana frame) {
        this.num_barbero = num_barbero;
        this.silla_barbero = false;
        this.frame = frame;
    }
    
     @Override
    public void run() {
        try {
            this.frame.EstadoBarbero(2, num_barbero);
            this.cortando_pelo = true;
            Thread.sleep(5000); //Proceso de cortar el pelo
            this.frame.EstadoBarbero(3, num_barbero);
            this.acabarCorte(this.num_barbero);
            Thread.sleep(1000); //Proceso de cortar el pelo
            this.frame.EstadoBarbero(1, num_barbero);
            Thread.sleep(1000); //Proceso de cortar el pelo
            this.cortando_pelo = false;
        } catch (InterruptedException e) {
            System.out.println("Error Exception en Barbero run... :(");
        }
    }
    
    public synchronized void barbero_esperando(int barbero) throws InterruptedException {
        barbero_durmiendo = true;
        while (!silla_barbero) {
            System.out.println(">_Barbero No: "+ barbero+" durmiendo en su silla");
            wait();
        }
        barbero_durmiendo = false;
        System.out.println(">_Barbero No: "+barbero+" esta despierto");
    }
    
    public synchronized void despertar_barbero() throws InterruptedException {
        notifyAll();
    }
    
    public synchronized void acabarCorte(int barbero) {
        System.out.println(">_Barbero No: "+ barbero +"termino de cortar el pelo");
        this.silla_barbero = false;
    }
     
   
}
